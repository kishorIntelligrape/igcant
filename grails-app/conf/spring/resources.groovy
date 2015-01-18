import grails.rest.render.json.JsonCollectionRenderer
import grails.rest.render.json.JsonRenderer
import ig.canteen.order.SoldItem
import ig.canteen.user.User
import ig.im.rest.renderer.UserJsonRenderer
import ig.im.rest.renderer.UserXmlRenderer

// Place your Spring DSL code here
beans = {

    userXmlRenderer (UserXmlRenderer)
    userJsonRenderer (UserJsonRenderer)
    userCollectionJsonRenderer (JsonCollectionRenderer, User) {
        includes = ['id', 'email', 'employeeCode']
    }
    soldItemCollectionJsonRenderer (JsonCollectionRenderer, SoldItem) {
        includes = ['user.email','productCategory', 'quantity', 'pricePerUnit', 'dateCreated']
    }
    soldItemJsonRenderer (JsonRenderer, SoldItem) {
        includes = ['user.email','productCategory', 'quantity', 'pricePerUnit', 'dateCreated']
    }
}
