<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:b="http://www.xmlproj.rs/gradjanin/sertifikat"
				xmlns:lp="http://www.xmlproj.rs/gradjanin/licniPodaci" version="2.0">

    <xsl:template match="/">
        <html>
            <head>
                <style type="text/css">
                    body { font-family: "Times New Roman";
                    margin-left:100px; margin-right:300px;}
                    img { max-width: 200px; height: 100px;}
                    .test:not(:last-child) {
                    content: "";
                    border-right: 1px solid black;
                    }
                    .horizontal-line {
                    border-bottom: 1px solid black;
                    }
                    .test-div {
                    display: flex;
                    justify-content: start;
                    margin-bottom:0px;
                    padding-bottom:0px;
                    margin-left:5px;
                    }

                    .test-info {
                    display:block;
                    font-size: medium;
                    margin-left:5px;
                    }

                    .div-between {
                    display: flex;
                    justify-content: space-between;
                    }

                    .div-start {
                    display: flex;
                    justify-content: start;
                    }

                    .p-info {
                    padding-top:20px;
                    float: left; width:300px;
                    font-size:medium;
                    }
					
					.row {
					display: flex;
					}

					.column1 {
					flex: 50%;
					}

                </style>
                <title>Digitalni zeleni sertifikat</title>
            </head>
            <body>
                <div class="div-between">
                    <div class="div-start" style="width:200px; text-align: center;">
                        <div style="display:block;">
                            <img src="https://www.srbija.gov.rs/img/logo_60x120-2.png" alt=""/>
                            <p>REPUBLIKA SRBIJA
                                REPUBLIC OF SERBIA
                            </p>
                        </div>
                    </div>


                    <div style="display: flex;justify-content: center;">
                        <div style="display:block;">
                            <p style="font-size:large;text-align:center">
                                <b>DIGITALNI ZELENI SERTIFIKAT</b>
                            </p>
                            <div style="text-align:center;">
                                Potvrda o izvršenoj vakcinaciji protiv
                                COVID-19 i rezultatima testiranja
                                <br/>
                                <b>DIGITAL GREEN CERTIFICATE</b>
                                <br/>Certificate of vaccination against COVID-19 and test results
                            </div>
                        </div>

                    </div>
                    <div style="display:flex; justify-content: end;">
                        <img style="width:150px;height:150px;" alt="" title="">
                            <xsl:variable name="id"
                                          select="b:Sertifikat/@broj_sertifikata"/>
                            <xsl:variable name="src"
                                          select="concat('https://api.qrserver.com/v1/create-qr-code/?data=','',$id)"/>
                            <xsl:attribute name="src">
                                <xsl:value-of select="$src"/>
                            </xsl:attribute>
                        </img>
                    </div>
                </div>
                <div class="div-between" style="margin-top:20px;height:50px">
                    <div class="div-between">
                        <p class="p-info">
                            <b>Broj sertifikata / Certificate ID:</b>
                        </p>
                        <p style="padding-top:20px;font-size:medium;">
                            <xsl:value-of
                                    select="b:Sertifikat/@broj_sertifikata"/>
                        </p>
                    </div>
                    <div class="div-between">
                        <p style="padding-top:20px; float: right; width:400px; font-size:medium;">
                            <b>Datum i vreme izdavanja sertifikata / Certificate issuing date and time:</b>
                        </p>
                        <p style="padding-top:35px;font-size:medium;">
                            <xsl:variable name="datetime"
                                          select="b:Sertifikat/b:Vreme_izdavanja"/>
                            <xsl:variable name="date" select="substring-before($datetime, 'T')"/>
                            <xsl:variable name="time" select="substring-after($datetime, 'T')"/>
                            <xsl:value-of select="concat($date,' ',$time)"/>
                        </p>
                    </div>
                </div>
                <div class="div-between" style="height:50px">
                    <div class="div-between">
                        <p class="p-info">
                            <b>Ime i prezime / Name and surname:</b>
                        </p>
                        <p style="padding-top:20px;font-size:medium;">
                            <xsl:value-of select="concat(b:Sertifikat/lp:Licni_podaci/lp:Ime,' ',b:Sertifikat/lp:Licni_podaci/lp:Prezime)"/>
                        </p>
                    </div>
                </div>
                <div class="div-between" style="height:50px">
                    <div class="div-between">
                        <p class="p-info">
                            <b>Pol / Gender:</b>
                        </p>
                        <p style="padding-top:20px;font-size:medium;">
                            <xsl:if test="contains(b:Sertifikat/lp:Licni_podaci/lp:Pol, 'Zenski')">
                                Ženski / Female
                            </xsl:if>
                            <xsl:if test="contains(b:Sertifikat/lp:Licni_podaci/lp:Pol, 'Muski')">
                                Muški / Male
                            </xsl:if>
                        </p>
                    </div>
                </div>
                <div class="div-between" style="height:50px">
                    <div class="div-between">
                        <p class="p-info">
                            <b>Datum rođenja / Date of birth:</b>
                        </p>
                        <p style="padding-top:20px;font-size:medium;">
                            <xsl:value-of select="b:Sertifikat/lp:Licni_podaci/lp:Datum_rodjenja"/>
                        </p>
                    </div>
                </div>
                <div class="div-between" style="height:50px">
                    <div class="div-between">
                        <p class="p-info">
                            <b>JMBG / Presonal No. / EBS:</b>
                        </p>
                        <p style="padding-top:20px;font-size:medium;">
                            <xsl:value-of select="b:Sertifikat/lp:Licni_podaci/lp:Jmbg"/>
                        </p>
                    </div>
                </div>
                <div class="div-between" style="height:50px;padding-bottom:10px;">
                    <div class="div-between">
                        <p class="p-info">
                            <b>Broj pasoša / Passport No.</b>
                        </p>
                        <p style="padding-top:20px;font-size:medium;">
                            <xsl:value-of select="b:Sertifikat/lp:Licni_podaci/lp:Br_pasosa"/>
                        </p>
                    </div>
                </div>
                <div class="horizontal-line"/>
                <div style="display: flex; justify-content: center;height:50px">
                    <p style="padding-top:5px; float: center; width:300px; font-size:large;">
                        <b>Vakcinacija / Vaccination</b>
                    </p>
                </div>
                <div class="div-between" style="width:400px;">

                    <xsl:for-each select="b:Sertifikat/b:Vakcinacije/b:Vakcinacija">

                        <div style="display:block;" class="test">

                            <div class="div-between">
                                <p style="float: left; width:150px; font-size:medium;margin-left:5px;">
                                    <b>Doza / Dose:</b>
                                </p>
                                <p class="div-info" style="width:400px;margin-left:2px;">
                                    <xsl:value-of select="b:Doza"/>/2
                                </p>
                            </div>
                            <div class="div-between">
                                <p style="float: left; width:150px; font-size:medium;margin-left:5px;">
                                    <b>Tip / Type:</b>
                                </p>
                                <p class="div-info" style="width:400px;margin-left:2px;">
                                    <xsl:value-of select="b:Naziv"/>
                                </p>
                            </div>
                            <div class="div-start" style="margin-bottom:0px;padding-bottom:0px; margin-left:2px;">
                                <p class="div-info" style="margin-left:5px;">
                                    <b>Proizvodjač i serija / Manufacturer and batch number:</b>
                                </p>

                            </div>
                            <div class="div-start" style="margin-left:5px;">
                                <p class="div-info" style="margin-left:5px;">
                                    <xsl:value-of select="b:Proizvodjac"/>
                                </p>
                            </div>
                            <div class="div-between">
                                <p class="div-info" style="margin-left:5px;">
                                    <b>Datum / Date:</b>
                                </p>
                                <p class="div-info" style="width:400px;margin-left:2px;">
                                    <xsl:value-of select="b:Datum_primanja"/>
                                </p>
                            </div>
                            <div class="div-start" style="margin-bottom:0px;padding-bottom:0px; margin-left:2px;">
                                <p class="div-info" style="margin-left:5px;">
                                    <b>Zdravstvena ustanova / Health care institution:</b>
                                </p>

                            </div>
                            <div class="div-start" style="margin-left:5px;">
                                <p class="div-info" style="margin-left:5px;">
                                    <xsl:value-of select="b:Ustanova"/>
                                </p>
                            </div>
                        </div>

                    </xsl:for-each>
                </div>

                <div class="horizontal-line"/>
                <div class="div-between" style="width:480px;">
                    
                        
                   

                </div>
                <div class="horizontal-line"/>
                <div class="div-between">
                    

                      
                    
                </div>
                <div class="div-between">
                    <div style="float:left; width:270px; text-align: center;margin-top:20px;">
                        <img style="float:left;" src="https://euprava.gov.rs/media/logos/Batut.gif" alt=""/>
                        <div class="div-start">
                            <p style=" float: left; width:250px; font-size:medium;text-align:left;margin-left:10px;margin-top:0px;">
                                <b>Sertifikat izdaje:</b>
                                Institut za javno zdravlje Srbije "Dr Milan Jovanović Batut"
                                <b>Certificate issued by:</b>
                                Institute of Public Health of Serbia "Dr Milan Jovanović Batut"
                            </p>
                        </div>
                    </div>
                    <div style="float:right; width:280px; text-align: center;margin-top:20px;">
                        <b>Digitalni potpis / Digitally signed by:</b>
                        <img style="float:left;" src="https://www.srbija.gov.rs/img/logo_60x120-2.png" alt=""/>
                        <div class="div-start" style="margin-top:5px;">
                            <p style=" float: left; width:250px; font-size:medium;text-align:left;margin-left:10px;margin-top:0px;">
                                REPUBLIKA SRBIJA Vlada Republike Srbije Kancelarija za informacione tehnologije i
                                elektronsku upravu
                                Nemanjina 11, BEOGRAD
                                Datum:
                                <xsl:variable name="datetime"
                                              select="b:Sertifikat/b:Vreme_izdavanja"/>
                                <xsl:variable name="date"
                                              select="substring-before($datetime, 'T')"/>
                                <xsl:value-of
                                        select="$date"/>
                            </p>
                        </div>
                    </div>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>