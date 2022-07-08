<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:b="http://www.xmlproj.rs/gradjanin/potvrda" 
				xmlns:lp="http://www.xmlproj.rs/gradjanin/licniPodaci" version="2.0">

    <xsl:template match="b:Potvrda">
        <head>
            <style type="text/css">
                .row {
                display: flex;
                }

                .column1 {
                flex: 50%;
                }

                .column2 {
                flex: 10%;
                }

                .column3 {
                flex: 90%;
                }

                h1, h3 {
                margin-buttom: 0em;
                }

                #institut {
                margin-right: 50%;
                margin-top: 2%;
                }

                .informations {
                font-size: 20px;
                }

                .info {
                margin-left: 2%;
                margin-right: 1%
                }

                .info2 {
                margin-left: 10.3%;
                }


                .underline {
                border-bottom: 1px solid black;
                width: 55%;
                }

                .centring {
                text-align: center;
                }
                .row-padding {
                font-size: 20px;
                padding-top: 2px;
                padding-bottom: 2px;
                }

                .row-padding-5 {
                padding-top: 15px;
                padding-bottom: 15px;
                font-size: 20px;
                }

                .text-padding {
                padding-right: 15%;
                padding-bottom: 50px;
                padding-left: 15%;
                }
                .intend {
                padding-left: 10%;
                }

                .dotted{
                border-bottom: 1px dotted #000;
                text-decoration: none;
                }

                .div-start {
                display: flex;
                justify-content: start;
                }
                img { max-width: 400px; height: 200px;}
                .div-between {
                display: flex;
                justify-content: space-between;
                }

                .margin-row-padding {
                border: 1px solid #ec4420;
                padding-top: 15px;
                padding-bottom: 15px;
                font-size: 20px;
                }

                .nowrap {
                white-space: nowrap ;
                }

            </style>
        </head>
        <html>
            <body>
                <br/>
                <div class="text-padding">

                    <div class="div-between">
                        <div style="float:left; width:270px; text-align: center;margin-top:20px;">
                            <img style="float:left;" src="https://euprava.gov.rs/media/logos/Batut.gif" alt=""/>

                        </div>
                        <div>
                            <div >
                                <p style="text-align: center;margin-top:40px;">
                                    <div class="nowrap" style="font-size:17px;text-align: center"><b style="font-size:17px;">INSTITUT ZA JAVNO ZDRAVLJE SRBIJE</b></div>
                                    <div style="text-align: center">"Dr Milan Jovanović Batut"</div>
                                    <div class="nowrap" style="font-size:17px;text-align: center">INSTITUTE OF PUBLIC HEALTH OF SERBIA</div>
                                    <div style="text-align: center">"Dr Milan Jovanovic Batut"</div>
                                </p>
                            </div>
                        </div>
                    </div>
                    <p>Šifra potvrde vakcinacije: <xsl:value-of select="@Sifra"/></p>
                    <p style="font-size:14px;color:#808080">Confirmation code</p>
                    <br></br>
                    <h3 style="text-align: center"> POTVRDA O IZVRŠENOJ VAKCINACIJI PROTIV  COVID-19</h3>
                    <p style="text-align: center;font-size:13px">POTVRDA O IZVRŠENOJ VAKCINACIJI PROTIV COVID-19</p>
                    <p style="text-align: center;font-size:13px">CONFIRMATION OF THE  COVID-19 VACCINATION</p>
                    <br/>
                    <br/>
                    <p><b>Ime i prezime:</b> <xsl:value-of select="concat(lp:Licni_podaci/lp:Ime,' ',lp:Licni_podaci/lp:Prezime)"/></p>
                    <p style="font-size:14px;color:#808080">First and Last Name</p>
                    <p><b>Datum rođenja:</b> <xsl:value-of select="lp:Licni_podaci/lp:Datum_rodjenja"/></p>
                    <p style="font-size:14px;color:#808080">Date Of Birth</p>
                    <p><b>Pol: <xsl:value-of select="lp:Licni_podaci/lp:Pol"/></b></p>
                    <p style="font-size:14px;color:#808080">
					Gender: <xsl:if test="lp:Licni_podaci/lp:Pol = 'Muski'">Male</xsl:if><xsl:if test="lp:Licni_podaci/lp:Pol = 'Zenski'">Female</xsl:if></p>
                    <p><b>JMBG:</b> <xsl:value-of select="lp:Licni_podaci/lp:Jmbg"/></p>
                    <p style="font-size:14px;color:#808080">Personal No.</p>
                    <p><b>Datum davanja i broj serije prve doze vakcinacije:</b>
                        <xsl:if test="b:Primljene_doze/b:Doza/b:Br_doze = '1'">
							<xsl:value-of select="b:Primljene_doze/b:Doza/b:Datum_davanja"/>
						</xsl:if>
                    <b>, serija:</b> 
						<xsl:if test="b:Primljene_doze/b:Doza/b:Br_doze = '1'">
							<xsl:value-of select="b:Primljene_doze/b:Doza/b:Br_serije"/>
						</xsl:if>
                    </p>
                    <p style="font-size:14px;color:#808080">Vaccination Date</p>
                    <p><b>Datum davanja i broj serije druge doze vakcine:</b>
                        <xsl:if test="b:Primljene_doze/b:Doza/b:Br_doze = '2'">
							<xsl:value-of select="b:Primljene_doze/b:Doza/b:Datum_davanja"/>
						</xsl:if>
                    <b>, serija:</b> 
						<xsl:if test="b:Primljene_doze/b:Doza/b:Br_doze = '2'">
							<xsl:value-of select="b:Primljene_doze/b:Doza/b:Br_serije"/>
						</xsl:if>
                    </p>
                    <p style="font-size:14px;color:#808080">Second Vaccination Date</p>
                    <p><b>Zdravstvena ustanova koja vakciniše:</b><xsl:value-of select="b:Zdravstvena_ustanova"/></p>
                    <p style="font-size:14px;color:#808080">Health care institution of vaccination</p>
                    <p><b>Naziv vakcine:</b> <xsl:value-of select="b:Naziv_vakcine"/></p>
                    <p style="font-size:14px;color:#808080">Name of vaccine</p>
                    <p><b>Datum izdavanja potvrde:</b>  <xsl:value-of select="b:Datum_izdavanja"/></p>
                    <p style="font-size:14px;color:#808080">Confirmation Release Date</p>
                    <br/>
                    <div class="row">
                        <div class="column1">
                        </div>
                        <div class="column1" style="text-align: left">
                            <p><b>Zdravstvena ustanova:  <xsl:value-of select="b:Zdravstvena_ustanova"/></b></p>
                            <p style="font-size:14px;color:#808080">Medical institution</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="column1">
                            <p><b>Ova potrvda važi bez potpisa i pečata </b></p>
                            <p style="font-size:14px;color:#808080">This certificate is valid without signatures and seals</p>
                        </div>
                        <div class="column1">
                            <div style="display:flex; justify-content: end;">
                            <img style="width:150px;height:150px;" alt="" title="">
                                <xsl:variable name="id"
                                              select="@Sifra"/>
                                <xsl:variable name="src"
                                              select="concat('https://api.qrserver.com/v1/create-qr-code/?data=localhost:4201/prikaz/potvrda_o_vakcinaciji/','',$id)"/>
                                <xsl:attribute name="src">
                                    <xsl:value-of select="$src"/>
                                </xsl:attribute>
                            </img>
                            </div>
                        </div>
                    </div>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>