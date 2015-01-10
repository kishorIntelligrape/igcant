package ig.canteen.user

class User {

    String firstName
    String lastName
    String employeeCode
    String token

    static constraints = {
        employeeCode unique: true
        token nullable: true
    }
}
