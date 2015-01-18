package ig.canteen.order

import grails.plugin.springsecurity.annotation.Secured
import ig.canteen.user.User
import org.springframework.http.HttpStatus

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK

@Secured("permitAll")
class SoldItemController  {

   static responseFormats = ['json', 'xml', 'hal']
   static allowedMethods = [save: "POST", update: "PUT", patch: "PATCH", delete: "DELETE"]

   /**
    * Lists all resources up to the given maximum
    *
    * @param max The maximum
    * @return A list of resources
    */
   def index(Integer max) {
      log.info("index: calling to get all purchased item")
      params.max = Math.min(max ?: 10, 100)
      User user = getuser()
      if (!user) {
         response.status = 422
         render  '[{"responce":"Not authenticated"}]'
         return
      }
      List<SoldItem> soldItems = listAllResources(user, params)
      respond soldItems, model: [("soldItemCount".toString()): soldItems.size()]
   }

   def show(){
      User user = getuser()
      if (!user) {
         response.status = 422
         render  '[{"responce":"Not authenticated"}]'
         return
      }
      SoldItem soldItem = SoldItem.findByUserAndId(user,params.id, params)
      HttpStatus status= OK
      if(!soldItem) {
         status = NOT_FOUND
      }
      respond soldItem, [status:  status]
   }


   def save() {
      User user = getuser()
      if (!user) {
         response.status = 422
         render  '[{"responce":"Not authenticated"}]'
         return
      }
      SoldItem soldItem = createResource(user)

      if (!soldItem.validate()){
         respond soldItem, [status: NOT_ACCEPTABLE]
      }
      soldItem.save(flush: true)

      respond soldItem, [status: CREATED]
   }

   /**
    * List all of resource based on parameters
    *
    * @return List of resources or empty if it doesn't exist
    */
   protected List<SoldItem> listAllResources(User user, Map params) {
      SoldItem.findAllByUser(user, params)
   }

   /**
    * Creates a new instance of the resource.  If the request
    * contains a body the body will be parsed and used to
    * initialize the new instance, otherwise request parameters
    * will be used to initialized the new instance.
    *
    * @return The resource instance
    */
   protected SoldItem createResource(User user) {
      SoldItem instance = new SoldItem()
      bindData instance, params
      instance.user = user
      return  instance
   }

   private User getuser() {
      User user = User.findByTokenAndEmail(params.userToken, params.email)
      user
   }
}
