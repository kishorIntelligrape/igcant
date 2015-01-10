package ig.canteen.user

class User {

    String firstName
    String lastName
    String employeeCode
    String token
    String email

    static constraints = {
        employeeCode unique: true
        token nullable: true
        email email: true, nullable: true, unique: true
    }
}
