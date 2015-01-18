package ig.im.rest.renderer

import grails.converters.JSON
import grails.rest.render.AbstractRenderer
import grails.rest.render.RenderContext
import ig.canteen.user.User
import org.codehaus.groovy.grails.web.mime.MimeType

/**
 * Created by intelligrape on 29/11/14.
 */
class UserJsonRenderer extends AbstractRenderer<User> {

    UserJsonRenderer() {
        super(User, [MimeType.JSON, MimeType.TEXT_JSON] as MimeType[])
    }

    @Override
    public void render(User user, RenderContext context) {
        context.contentType = MimeType.JSON.name

        JSON converter
        def detail = context.arguments?.detail ?: 'compact'
        println ("details: detail")
        JSON.use(detail) {
            converter = user as JSON
        }

        /*if (context.arguments?.author) {

        }*/


        converter.render(context.writer)
    }
}
