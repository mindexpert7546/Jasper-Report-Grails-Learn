package jasperreport
import net.sf.jasperreports.engine.JasperFillManager
import net.sf.jasperreports.engine.JasperCompileManager
import net.sf.jasperreports.engine.JasperExportManager
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource

class ProductController {

    def index() {
        /// Fetch data from the database or any other source
        def data = Product.list()

        // Choose a specific product for demonstration (you might want to fetch it differently)
        def specificProduct = data.first()

        // Compile the JasperReport template
        //perfect path : C:\Users\Kundan Kumar\Desktop\jasperReport\src\main\webapp\reports/product.jrxml
        def reportPath = grailsApplication.parentContext.getResource("./reports/product.jrxml").file.toString()
        def jasperReport = JasperCompileManager.compileReport(reportPath)

        // Create parameters map
        def fileParams = [:]
        fileParams.put("name", specificProduct.name)

        // Fill the report with data and parameters
        def jasperPrint = JasperFillManager.fillReport(
            jasperReport,
            fileParams,
            new JRBeanCollectionDataSource(data)
        )

        // Export the report to PDF and send it as the response
        response.setHeader('Content-Disposition', 'inline; filename=ProductReport.pdf')
        response.setContentType('application/pdf')
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.outputStream)
    }


}
