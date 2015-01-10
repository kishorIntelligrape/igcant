class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/user" (resources: 'user')
        "/soldItem" (resources: 'soldItem')

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
