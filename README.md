# Jasper-Report-Grails-Learn

## Learn More : 
https://github.com/mindexpert7546/Jasper-Report-Grails-Learn/blob/main/GetStart.md

## Some Screen Shot : 
![Screenshot 2023-11-14 164759](https://github.com/mindexpert7546/Jasper-Report-Grails-Learn/assets/89348788/69f0495d-aa44-4658-ab85-0a5e3706384f)
![Screenshot 2023-11-14 164749](https://github.com/mindexpert7546/Jasper-Report-Grails-Learn/assets/89348788/316ef114-bb3f-4bd5-88e8-1bbfbc05144e)
![Screenshot 2023-11-14 164809](https://github.com/mindexpert7546/Jasper-Report-Grails-Learn/assets/89348788/35dec9c9-ed84-4dad-93f7-4ef65c4887e0)
![Screenshot 2023-11-14 164817](https://github.com/mindexpert7546/Jasper-Report-Grails-Learn/assets/89348788/9871795a-6a62-49c4-b712-48e5f9ecd947)
![Screenshot 2023-11-14 164823](https://github.com/mindexpert7546/Jasper-Report-Grails-Learn/assets/89348788/fa15b90e-ce0e-4a4b-b487-242a22e1a064)
![Screenshot 2023-11-14 164840](https://github.com/mindexpert7546/Jasper-Report-Grails-Learn/assets/89348788/2aafaf11-545f-40a0-96a1-4cba4d3b06d2)
![Screenshot 2023-11-14 164936](https://github.com/mindexpert7546/Jasper-Report-Grails-Learn/assets/89348788/ad873a97-a567-4fa1-a5be-2635b359478c)

## Dependency grails 3.x 
```
       compile "com.lowagie:itext:2.1.7"
    compile("net.sf.jasperreports:jasperreports:5.6.1") {
    exclude group: 'antlr', module: 'antlr'
    exclude group: 'commons-logging', module: 'commons-logging'
    exclude group: 'org.apache.ant', module: 'ant'
    exclude group: 'mondrian', module: 'mondrian'
    exclude group: 'commons-javaflow', module: 'commons-javaflow'
    exclude group: 'net.sourceforge.barbecue', module: 'barbecue'
    exclude group: 'xml-apis', module: 'xml-apis'
    exclude group: 'xalan', module: 'xalan'
    exclude group: 'org.codehaus.groovy', module: 'groovy-all'
    exclude group: 'org.hibernate ', module: 'hibernate'
    exclude group: 'javax.xml.soap', module: 'saaj-api'
    exclude group: 'javax.servlet', module: 'servlet-api'
    exclude group: 'org.springframework', module: 'spring-core'
    exclude group: 'org.beanshell', module: 'bsh'
    exclude group: 'org.springframework', module: 'spring-beans'
    exclude group: 'jaxen', module: 'jaxen'
    exclude group: 'net.sf.barcode4j ', module: 'barcode4j'
    exclude group: 'org.apache.xmlgraphics', module: 'batik-svg-dom'
    exclude group: 'org.apache.xmlgraphics', module: 'batik-xml'
    exclude group: 'org.apache.xmlgraphics', module: 'batik-awt-util'
    exclude group: 'org.apache.xmlgraphics', module: 'batik-dom'
    exclude group: 'org.apache.xmlgraphics', module: 'batik-css'
    exclude group: 'org.apache.xmlgraphics', module: 'batik-gvt'
    exclude group: 'org.apache.xmlgraphics', module: 'batik-script'
    exclude group: 'org.apache.xmlgraphics', module: 'batik-svggen'
    exclude group: 'org.apache.xmlgraphics', module: 'batik-util'
    exclude group: 'org.apache.xmlgraphics', module: 'batik-bridge'
    exclude group: 'javax.persistence', module: 'persistence-api'
    exclude group: 'eclipse', module: 'jdtcore'
    exclude group: 'org.olap4j', module: 'olap4j'
}

    compile "org.apache.poi:poi:3.10-FINAL"
    compile "commons-io:commons-io:2.5"
    compile 'net.sf.jasperreports:jasperreports:6.20.0'
```
### Sample code for Domain class : 

```
package jasperreport

class Product {
    
    String name
    static constraints = {
    }
}
```

## Sample code for Controller : 
```
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
```
## Sample code for jrxml : 
```

<?xml version="1.0" encoding="UTF-8"?>
<!-- Created with Jaspersoft Studio version 6.20.6.final using JasperReports Library version 6.20.6-5c96b6aa8a39ac1dc6b6bea4b81168e16dd39231  -->
<jasperReport xmlns="http://jasperreports.sourceforge.net/jasperreports" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://jasperreports.sourceforge.net/jasperreports http://jasperreports.sourceforge.net/xsd/jasperreport.xsd" name="product" pageWidth="595" pageHeight="842" columnWidth="555" leftMargin="20" rightMargin="20" topMargin="20" bottomMargin="20" uuid="ec4661cc-6d15-4c6c-abe3-f9c2f57d212d">
	<property name="com.jaspersoft.studio.data.defaultdataadapter" value="One Empty Record"/>
	<parameter name="name" class="java.lang.String"/>
	<queryString>
		<![CDATA[]]>
	</queryString>
	<background>
		<band splitType="Stretch"/>
	</background>
	<title>
		<band height="317" splitType="Stretch">
			<frame>
				<reportElement x="30" y="80" width="500" height="100" uuid="f3be2f9d-f349-4669-8bdb-2ed83fda627d"/>
				<staticText>
					<reportElement mode="Opaque" x="20" y="30" width="219" height="40" backcolor="#FA2E2A" uuid="9b7cafe5-8e32-433a-932b-22e4d673dd9d"/>
					<textElement>
						<font size="26"/>
					</textElement>
					<text><![CDATA[Product Report]]></text>
				</staticText>
				<textField>
					<reportElement x="300" y="30" width="150" height="40" uuid="670af8ab-5992-48da-9244-23c069718f37"/>
					<textElement>
						<font size="26"/>
					</textElement>
					<textFieldExpression><![CDATA[$P{name}]]></textFieldExpression>
				</textField>
			</frame>
		</band>
	</title>
</jasperReport>
```
## Ref Link : 
http://beginninggroovyandgrails.blogspot.com/2017/05/migration-to-grails-3.html


