<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:b="http://www.xmlproj.rs/gradjanin/interesovanje"
				xmlns:lp="http://www.xmlproj.rs/gradjanin/licniPodaci"
				version="2.0">

    <xsl:template match="b:Interesovanje">
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

                .sublabel2:after {
                content: "(бр. пасоша или ЕБС за стране држављане)";
                display: block;
                font-size: 18px;
                color: #000;
                margin-top: 10px;
                margin-left: 30%;
                }

                .sublabel1:after {
                content: "(назив страног држављанства)";
                display: block;
                font-size: 18px;
                color: #000;
                margin-top: 10px;
                margin-left: 30%;
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
                padding-right: 20%;
                padding-bottom: 50px;
                padding-left: 20%;
                }
                .intend {
                padding-left: 10%;
                }

                .dotted{
                border-bottom: 1px dotted #000;
                text-decoration: none;
                }

                .margin-row-padding {
                border: 1px solid #ec4420;
                padding-top: 15px;
                padding-bottom: 15px;
                font-size: 20px;
				color: blue;
                }

            </style>
        </head>
        <html>
            <body>

                <br/>
                <h2 class="centring">Iskazivanje interesovanja za vakcinisanje protiv COVID-19</h2>
                <div class="text-padding">
                    <div class="row-padding">Odaberite opciju:</div>
                    <div class="intend">
                        <div class="row">
                            <div class="column1">
                                <xsl:if test="b:Opcija='drzavljanin_rs'">
                                    <div class="margin-row-padding"> Državljanin Republike Srbije</div>
                                </xsl:if>
                                <xsl:if test="b:Opcija!='drzavljanin_rs'">
                                    <div class="row-padding"> Državljanin Republike Srbije</div>
                                </xsl:if>
                            </div>
                            <div class="column1">
                                <xsl:if test="b:Opcija='strani_sa_boravkom_u_rs'">
                                    <div class="margin-row-padding"> Strani državljanin sa boravkom u RS</div>
                                </xsl:if>
                                <xsl:if test="b:Opcija!='strani_sa_boravkom_u_rs'">
                                    <div class="row-padding"> Strani državljanin sa boravkom u RS</div>
                                </xsl:if>
                            </div>
                            <div class="column1">
                                <xsl:if test="b:Opcija='strani_bez_boravka_u_rs'">
                                    <div class="margin-row-padding"> Strani državljanin bez boravka u RS</div>
                                </xsl:if>
                                <xsl:if test="b:Opcija!='strani_bez_boravka_u_rs'">
                                    <div class="row-padding"> Strani državljanin bez boravka u RS</div>
                                </xsl:if>
                            </div>
                        </div>
                    </div>
                    <br/>
                    <div class="row-padding">JMBG:</div>
                    <div class="row-padding-5">.......................................................................<xsl:value-of select="lp:Licni_podaci/lp:Jmbg"/>.......................................................................</div>
                    <div class="row-padding">Ime:</div>
                    <div class="row-padding-5">.......................................................................<xsl:value-of select="lp:Licni_podaci/lp:Ime"/>.......................................................................</div>
                    <div class="row-padding">Prezime:</div>
                    <div class="row-padding-5">.......................................................................<xsl:value-of select="lp:Licni_podaci/lp:Prezime"/>.......................................................................</div>
                    <div class="row-padding">Adresa elektronske pošte:</div>
                    <div class="row-padding-5">.......................................................................<xsl:value-of select="lp:Licni_podaci/lp:Kontakt/lp:Email"/>.......................................................................</div>
                    <div class="row-padding">Broj mobilnog telefona (navesti broj u formatu 06X-XXXXXX(X)):</div>
                    <div class="row-padding-5">.......................................................................<xsl:value-of select="lp:Licni_podaci/lp:Kontakt/lp:Broj_mobilnog"/>.......................................................................</div>
                    <div class="row-padding">Broj fiksnog telefona (navesti broj u formatu 011-XXXX-XXX):</div>
                    <div class="row-padding-5">.......................................................................<xsl:value-of select="lp:Licni_podaci/lp:Kontakt/lp:Broj_fiksnog"/>.......................................................................</div>
                    <div class="row-padding">Odaberite lokaciju gde želite da primite vakcinu (unesite opštinu):</div>
                    <div class="row-padding-5">.......................................................................<xsl:value-of select="lp:Licni_podaci/lp:Opstina"/>.......................................................................</div>
                    <br/>
                    <div class="row-padding">Iskazujem interesovanje da primim isključivo vakcinu sledećih proizvođača za
                        koji Agencija za lekove i medicinska sredstva potvrdi bezbednost, efikasnost i
                        kvalitet i izda dozvolu za upotrebu leka:
                    </div>
                    <div class="intend">
					<xsl:for-each select="b:Izabrane_vakcine/b:Vakcina">
                        <div class="row">
                            <div class="column1">
                                <xsl:value-of select="concat(., ' ')"/>
                            </div>
                        </div>
					</xsl:for-each>
                    </div>
                    <br/>
                    <div class="row-padding">Da li ste dobrovoljni davalac krvi?</div>
                    <div class="intend">
                        <div class="row">
                            <div class="column1">
                                <xsl:if test="b:Davalac_krvi='true'">
                                    <div class="margin-row-padding"> Da</div>
                                </xsl:if>
                                <xsl:if test="b:Davalac_krvi!='true'">
                                    <div class="row-padding"> Da</div>
                                </xsl:if>
                            </div>
                             <div class="column1">
                                <xsl:if test="b:Davalac_krvi='false'">
                                    <div class="margin-row-padding"> Ne</div>
                                </xsl:if>
                                <xsl:if test="b:Davalac_krvi!='false'">
                                    <div class="row-padding"> Ne</div>
                                </xsl:if>
                            </div>
                        </div>
                    </div>
                    <br/>
                    <br/>
                    <br/>
                    <br/>
                    <div class="row">
                        <div class="column1">
                            <div class="row-padding">dana <xsl:value-of select="b:Datum"/> </div>
                        </div>
                        <div class="column1">
                            <div>
                                <div class="centring row-padding">__________________ </div>
                                <div class="centring row-padding">Potpis</div>
                            </div>

                        </div>
                    </div>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>