<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:b="http://www.xmlproj.rs/gradjanin/zahtev"
				xmlns:lp="http://www.xmlproj.rs/gradjanin/licniPodaci" version="2.0">

    <xsl:template match="b:Zahtev">
        
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
				
				body {
					margin-right: 10em;
					margin-left: 10em;
				}
	
            	h2 {
            		margin-top: 5em;
            		text-align: center;
            	}
            
            	h3 {
            		text-align: center;
            	}
            	
            	.paragraph {
            		margin-top: 3em;
            		margin-bottom: 2em;
            		text-indent: 2em;
            	}
			
				.signature {
					float: right;
					margin-top: 1em;
					width: 30%;
					border-top: 1px solid black;
				}
				
				.signature:after {
                    content: "Potpis";
                    display: block;
                    text-align: center;
                 }
                 
				.reason-hint:after {
					content: "";
                    display: block;
                    font-size: 12px;
                    text-align: center;
				}
				
                 .container {
                 	display: flex;
                 	width: 100%;
                 }
                 
                 .label {
                 	margin-right: 0.25em;
                 }
                 
                 .value {
                 	flex-grow: 1;
                 	border-bottom: 1px dotted black;
                 }
                 
                 .reason {
                 	margin-top: 1em;
                 }
                 
                 .reason .label {
                 	margin-bottom: 0.5em;
                 }
                 
                 .place {
                 	margin-top: 4em;
                 	margin-bottom: 1em;
                 }
            	</style>
        	</head>
            <body>
            	<h2>ZAHTEV</h2>
            	<h3>za izdavanje digitalnog zelenog sertifikata</h3>
            	<p class="paragraph">
            		U skladu sa odredbom Republike Srbije o izdavanju digitalnog zelenog 
					sertifikata kao potvrde o izvršenoj vakcinaciji protiv COVID-19, rezultatima 
					testiranja na zaraznu bolest SARS-CoV-2 ili oporavku od bolesti COVID-19, 
					podnosim zahtev za izdavanje digitalnog zelenog sertifikata. 
            	</p>
          
            	<p>Podnosilac zahteva:</p>
            	<div class="container">
            		<div class="label">
            			Ime i prezime:
            		</div>
            		<div class="value">
            			<xsl:value-of select="concat(lp:Licni_podaci/lp:Ime,' ',lp:Licni_podaci/lp:Prezime)"></xsl:value-of>
            		</div>
            	</div>
            	
            	<div class="container">
            		<div class="label">
            			Datum rođenja:
            		</div>
            		<div class="value">
            			<xsl:value-of select="lp:Licni_podaci/lp:Datum_rodjenja"></xsl:value-of>
            		</div>
            	</div>
            	
            	<div class="container">
            		<div class="label">
            			Pol:
            		</div>
            		<div class="value">
            			<xsl:if test="lp:Licni_podaci/lp:Pol = 'Muski'">
							Muški
						</xsl:if>
						<xsl:if test="lp:Licni_podaci/lp:Pol = 'Zenski'">
							Ženski
						</xsl:if>
            		</div>
            	</div>
            	
            	<div class="container">
            		<div class="label">
            			Jedinstveni matični broj građanina:
            		</div>
            		<div class="value">
            			<xsl:value-of select="lp:Licni_podaci/lp:Jmbg"></xsl:value-of>
            		</div>
            	</div>
            	<div class="container">
            		<div class="label">
            			Broj pasoša:
            		</div>
            		<div class="value">
            			<xsl:value-of select="lp:Licni_podaci/lp:Br_pasosa"></xsl:value-of>
            		</div>
            	</div>
            	<div class="reason">
            		<div class="label">
            			Razlog za podnošenje zahteva: (navesti što precizniji razloga za podnošenje zahteva za izdavanje digitalnog pasoša)
            		</div>
            		<div class="value">
						<xsl:value-of select="b:Razlog"/>
            		</div>
               	</div>
               	
               	<div class="reason-hint">
               	</div>
            	
            	<div class="place">
            		U <u><xsl:value-of select="b:Mesto"></xsl:value-of></u> ,
            	</div>
            	<div class="date">
            		dana <u><xsl:value-of select="b:Datum_podnosenja"></xsl:value-of></u> godine
            	</div>
				<br/>
            	<div class="centring row-padding">__________________ </div>
                <div class="centring row-padding">Potpis</div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
