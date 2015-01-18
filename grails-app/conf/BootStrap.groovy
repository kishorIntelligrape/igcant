import grails.converters.JSON
import ig.canteen.order.SoldItem
import ig.canteen.user.Role
import ig.canteen.user.User
import ig.canteen.user.UserRole
import ig.im.rest.marshaller.UserJsonMarshaller
import ig.im.rest.marshaller.UserJsonMarshallerCompact
import ig.im.utils.IgCanteenConstants

class BootStrap {

    def init = { servletContext ->
        if (!User.list()){
            User userAdmin = new User(email: "admin@igp.com", employeeCode: "IG000", username: "admin@igp.com", password: "igdefault").save(flush: true, failOnError: true)
            Role adminRole = new Role(authority: IgCanteenConstants.ROLE_ADMIN).save(flush: true)
            Role userRole = new Role(authority: IgCanteenConstants.ROLE_USER).save(flush: true)

            UserRole adminRoleMap = new UserRole(user: userAdmin, role: adminRole).save(flush: true)


            User user1 = new User(email: "test@igp.com", employeeCode: "IG001", username: "test@igp.com", password: "igdefault", token: "123").save(flush: true, failOnError: true)
            User user2 = new User(email: "test2@igp.com", employeeCode: "IG002", username: "test2@igp.com", password: "igdefault", token: "321").save(flush: true, failOnError: true)

            SoldItem sd1 = new SoldItem(user: user1, productCategory: "namkeen", pricePerUnit: 5, quantity: 2).save(flush: true, failOnError: true)
            SoldItem sd2 = new SoldItem(user: user1, productCategory: "namkeen", pricePerUnit: 10, quantity: 3).save(flush: true, failOnError: true)
            SoldItem sd3 = new SoldItem(user: user1, productCategory: "namkeen", pricePerUnit: 15, quantity: 1).save(flush: true, failOnError: true)
            SoldItem sd4 = new SoldItem(user: user1, productCategory: "namkeen", pricePerUnit: 30, quantity: 2).save(flush: true, failOnError: true)
            SoldItem sd5 = new SoldItem(user: user2, productCategory: "Biscuts", pricePerUnit: 5, quantity: 2).save(flush: true, failOnError: true)
            SoldItem sd6 = new SoldItem(user: user2, productCategory: "Biscuts", pricePerUnit: 10, quantity: 3).save(flush: true, failOnError: true)
            SoldItem sd7 = new SoldItem(user: user2, productCategory: "Biscuts", pricePerUnit: 15, quantity: 1).save(flush: true, failOnError: true)
            SoldItem sd8 = new SoldItem(user: user2, productCategory: "Biscuts", pricePerUnit: 30, quantity: 2).save(flush: true, failOnError: true)
        }
        JSON.createNamedConfig("compact") {
            it.registerObjectMarshaller (User, UserJsonMarshallerCompact.marshal)
        }
        JSON.createNamedConfig("complete") {
            it.registerObjectMarshaller (User, UserJsonMarshaller.marshal)
        }
    }
    def destroy = {
    }
}
