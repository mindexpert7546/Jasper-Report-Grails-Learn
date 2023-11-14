package jasperreport

class BootStrap {

    def init = { servletContext ->
    def products = new Product(name:"Laptop")
    products.save()
    }
    def destroy = {
    }
}
