<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:b="http://www.xmlproj.rs/sluzbenik/izvestaj" version="2.0">

    <xsl:template match="b:Izvestaj">
        
        <html>
        	<head>
            	<style type="text/css">
            	html, body {
    				height: 100%;
				}

				html {
    				display: table;
    				margin: auto;
				}
	
            	h3 {
            		margin-top: 5em;
            		text-align: center;
            	}
            	
            	.interval {
            		margin-top: 2em;
            		margin-bottom: 2em;
            	}
            	
            	.period {
            		margin-top: 4em;
            	}
            	
            	.order {
            		margin-top: 4em;
            	}
            	
            	table {
            		width: 100%;
            		margin-top: 1.5em;
            	}
            	
            	table, th, td {
  					border: 1px solid black;
  					border-collapse: collapse;
				}
				
				td {
  					text-align: center;
				}
				
				ul {
					margin-top: 0em;
				}
				
				.footer {
					margin-top: 4em;
					width: 100%;
					display: flex;
					justify-content: space-between;
				}
				
				.signature {
					margin-top: 1em;
					width: 30%;
					border-top: 1px solid black;
				}
				
				.signature:after {
                    content: "Potpis";
                    display: block;
                    text-align: center;
                 }
				 
				 
				 .centring {
                text-align: center;
                }
				
                .row-padding {
                padding-top: 2px;
                padding-bottom: 2px;
                }
            	</style>
        	</head>
            <body>
            	<h3>Izveštaj o imunizaciji</h3>
            	<div class="period">
            		Izveštaj se odnosi na period od <strong><xsl:value-of select="b:Period_od"></xsl:value-of></strong> do <strong><xsl:value-of select="b:Period_do"></xsl:value-of></strong>
            	</div>
            	<div class="interval">
            		U napomenutom vremenskom intervalu je:
            		<ul>
            			<li>podneto <strong><xsl:value-of select="b:Dokumenti/b:Podneto_interesovanja"></xsl:value-of></strong> dokumenata o interesovanju za imunizaciju; </li>
            	
            			<li>primljeno <strong><xsl:value-of select="b:Dokumenti/b:Primljeno_zahteva"></xsl:value-of></strong> zahteva za digitalni zeleni sertifikat, od kojih je 
            				<strong><xsl:value-of select="b:Dokumenti/b:Izdato_sertifikata"></xsl:value-of></strong> izdato.</li>
            		</ul> 
            	</div>
            	<div>
            		Dato je <strong><xsl:value-of select="b:Dato_vakcina"></xsl:value-of></strong> doza vakcine protiv COVID-19 virusa u sledećoj količini: 
					<table>
						<tr>
							<th>Redni broj doze</th>
							<th>Broj datih doza</th>
						</tr>
						<xsl:for-each select="b:Kolicine_doza/b:Doza">
							<tr>
								<td><strong><xsl:value-of select="b:Broj_doze"></xsl:value-of></strong></td>
								<td><xsl:value-of select="b:Kolicina"></xsl:value-of></td>
							</tr>
						</xsl:for-each>
					</table>
            	</div>
            	<div class="order">
            		Raspodela po proizvođačima je:
            		<ul>
            			<xsl:for-each select="b:Proizvodjaci/b:Proizvodjac">
            				<li><strong><xsl:value-of select="b:Naziv"></xsl:value-of> - <xsl:value-of select="b:Kolicina"></xsl:value-of></strong> doza;</li>
            			</xsl:for-each>
            		</ul> 
            	</div>
            	<div class="footer">
            		<div class="date"> 
						Datum izdavanja: <u><xsl:value-of select="b:Datum_izdavanja"></xsl:value-of></u> godine 
            		</div>
            		
					<div>
							<div class="centring row-padding">__________________ </div>
							<div class="centring row-padding">Potpis</div>
					</div>
            		
            	</div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
