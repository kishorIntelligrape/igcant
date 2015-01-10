package ig.canteen.user

import grails.test.mixin.Mock
import grails.test.mixin.domain.DomainClassUnitTestMixin
import grails.util.Mixin
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@Mock(User)
@Mixin([DomainClassUnitTestMixin])
class UserSpec extends Specification {

    def setup() {
        println ("**************** Bootstraping data *****************************************")
        new User (firstName: 'firstName', lastName: 'lastName', employeeCode: "IG011", token: "token", email: "firstName@igp.com").save(flush: true, failOnError: true)
        new User (firstName: 'firstName1', lastName: 'lastName1', employeeCode: "IG012", token: "token", email: "firstName1@igp.com").save(flush: true, failOnError: true)
        new User (firstName: 'firstName2', lastName: 'lastName2', employeeCode: "IG013", token: "token", email: "firstName2@igp.com").save(flush: true, failOnError: true)
        new User (firstName: 'firstName3', lastName: 'lastName3', employeeCode: "IG014", token: "token", email: "firstName3@igp.com").save(flush: true, failOnError: true)
        println ("**************** Bootstraping data End *****************************************")
    }

    def cleanup() {
    }

    @Unroll
    void "validate user token null allowed constraints"() {
        when:
        User user = new User(firstName: firstName, lastName: lastName, employeeCode: employeeCode, token: token)
        user.save(flush: true)
        println("RUning ${sno}")
        then:
        containError == user.hasErrors()

        where:
        sno | firstName | lastName   | employeeCode | token       | containError
        1   | "test"    | "lastName" | "IG001"      | null        | false
        2   | "test"    | "lastName" | "IG001"      | "alsdfjasl" | false
        3   | "test"    | "lastName" | "IG001"      | ""          | false
    }

    @Unroll
    void "test finder by firstName" () {
        when :
        User user = User.findByFirstName(firstName)
        println (user?.dump())

        then:
        containError == (user.lastName != lastName)

        where:
        sno | firstName    | lastName    | employeeCode | token       | containError
        1   | "firstName"  | "lastName"  | "IG0011"     | null        | false
        2   | "firstName1" | "lastName1" | "IG001"      | "alsdfjasl" | false
        3   | "firstName2" | "lastName" | "IG001"      | ""           | true
    }

    @Unroll
    void "test email constraints" () {

        when :
        User user = new User(firstName: firstName, lastName: lastName, employeeCode: employeeCode, token: token, email: email)
        println ("email : [${user.email}]")
        user.validate()
        println ("error: ${user.errors}")
        then:
        containError == user.hasErrors()

        where:
        sno | firstName   | lastName   | employeeCode | email               | token | containError
        1   | "firstName" | "lastName" | "IG0011"     | ''                  | null  | false
        2   | "firstName" | "lastName" | "IG0011"     | 'test'              | null  | true
        3   | "firstName" | "lastName" | "IG0011"     | 't@ig.com'          | null  | false
        4   | "firstName" | "lastName" | "IG0011"     | 't@ig.ig'           | null  | true
        5   | "firstName" | "lastName" | "IG0011"     | '@ig.com'           | null  | true
        6   | "firstName" | "lastName" | "IG0011"     | 't@ig.net'          | null  | false
        7   | "firstName" | "lastName" | "IG0011"     | '         '         | null  | false //TODO - Check why null is been set instead of string with space character
        7   | "firstName" | "lastName" | "IG0011"     | 'firstName@igp.com' | null  | true
    }
}
