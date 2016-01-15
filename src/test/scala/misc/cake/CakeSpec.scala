package misc.cake

import org.scalatest.{Matchers, path}

class CakeSpec extends path.FunSpec with Matchers {

  describe("Cake pattern") {
    trait UserRepositoryComponent {
      def userLocator: UserLocator

      def userUpdater: UserUpdater

      trait UserLocator {
        def findAll: List[User]
      }

      trait UserUpdater {
        def save(u: User)
      }

    }

    trait User

    trait Result {
      def getResultList: List[User]
    }

    trait EntityManager {
      def createQuery(from: String, cls: Class[_]): Result

      def persist(u: User)
    }

    trait UserRepositoryJPAComponent extends UserRepositoryComponent {
      val em: EntityManager

      def userLocator = new UserLocatorJPA(em)

      def userUpdater = new UserUpdaterJPA(em)

      class UserLocatorJPA(val em: EntityManager) extends UserLocator {
        def findAll = em.createQuery("from User", classOf[User]).getResultList
      }

      class UserUpdaterJPA(val em: EntityManager) extends UserUpdater {
        override def save(user: User) {
          em.persist(user)
        }
      }

    }

    trait UserServiceComponent {
      def userService: UserService

      trait UserService {
        def findAll: java.util.List[User]

        def save(user: User)
      }

    }

    //    trait DefaultUserServiceComponent extends UserServiceComponent {
    //      this: UserRepositoryComponent => def userService = new DefaultUserService
    //
    //      class DefaultUserService extends UserService {
    //        //def findAll = userLocator.findAll
    //        def save(user: User) {
    //          userUpdater.save(user: User)
    //        }
    //      }
    //    }
    //
    //    object ApplicationLive {
    //      val userServiceComponent = new DefaultUserServiceComponent with UserRepositoryJPAComponent {
    //        val em = Persistence.createEntityManagerFactory("cake.pattern"). createEntityManager()
    //      }
    //      val userService = userServiceComponent.userService
    //    }
    //  }
  }
}
