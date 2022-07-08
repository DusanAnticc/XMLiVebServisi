<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:b="http://www.xmlproj.rs/gradjanin/saglasnost"
				xmlns:lp="http://www.xmlproj.rs/gradjanin/licniPodaci"  version="2.0">

    <xsl:template match="b:Saglasnost">
        <html>
            <head>
                <style type="text/css">
                    .row {
                    display: flex;
                    }

                    .column1 {
                    flex: 50%;
                    margin-left: 3em;
                    }

                    .column2 {
                    flex: 10%;
                    }

                    .column3 {
                    flex: 90%;
                    }

                    h1, h3 {
                    margin-bottom: 0em;
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

                    .sublabel {
                    display: block;
                    font-size: 18px;
                    color: #000;
                    margin-left: 21%;
                    margin-top: 0em;
                    }

                    .underline {
                    border-bottom: 1px solid black;
                    }

                    input[type="checkbox"] {
                        background-color: #fff;
                        margin: 0;
                        font: inherit;
                        color: currentColor;
                        width: 1.15em;
                        height: 1.15em;
                        border: 0.10em solid currentColor;
                        border-radius: 0.15em;
                        transform: translateY(-0.075em);
                        display: grid;
                        place-content: center;
                    }

                    input[type="checkbox"]::before {
                        content: "";
                        width: 0.65em;
                        height: 0.65em;
                        transform: scale(0);
                        transition: 120ms transform ease-in-out;
                        box-shadow: inset 1em 1em var(--form-control-color);
                        transform-origin: bottom left;
                        clip-path: Polygon(14% 44%, 0 65%, 50% 100%, 100% 16%, 80% 0%, 43% 62%);
                    }

                    input[type="checkbox"]:checked::before {
                        transform: scale(1);
                    }

                    table, th, td {
                        border: 1px solid black;
                    }

                    th {
                        height: 5em;
                    }

                    td {
                        text-align: center;
                        height: 3em;
                    }
                </style>
            </head>
            <body>
                <div class="row">
                    <div class="column1">
                        <h1>SAGLASNOST ZA SPROVOĐENjE PREPORUČENE IMUNIZACIJE</h1>
                        <h4>(popunjava pacijent)</h4>
                    </div>
                    <div class="column1">
                        <div class="row">
                            <div class="column2">
                                <img src="https://euprava.gov.rs/media/logos/Batut.gif" alt="Girl in a jacket" height="150em" width="130em"/>
                            </div>
                            <div class="column3" id="institut">
                                <p>INSTITUT ZA JAVNO ZDRAVLJE SRBIJE "Dr Milan Jovanović Batut"</p>
                            </div>
                        </div>
                    </div>
                </div>
                <xsl:apply-templates select="b:Pacijentov_deo"/>
                <xsl:apply-templates select="b:Radnikov_deo"/>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="b:Pacijentov_deo">
        <div class="informations">
            <div class="row">
                <p class="info"><b>Državljanstvo</b></p>
                <p>1) Republika Srbija | JMBG </p>
                <p class="underline" style="width: 54%; margin-left: 0.5em;"><xsl:value-of select="lp:Licni_podaci/lp:Jmbg"/></p>
            </div>
            <div class="row">
                <p class="info2">2)</p>
                <p class="underline" style="width: 33%;"><xsl:value-of select="b:Drzavljanstvo"/></p>
                <p>|</p>
                <p class="underline" style="width: 33%;"> <xsl:value-of select="lp:Licni_podaci/lp:Br_pasosa"/></p>
            </div>
            <div class="row">
                <p class="sublabel">(naziv stranog državljanstva)</p>
                <p class="sublabel">(br. pasoša ili EBS za strane državljane)</p>
            </div>
            <div class="row">
                <p class="info"><b>Prezime</b></p>
                <p class="underline" style="width: 19%"><xsl:value-of select="lp:Licni_podaci/lp:Prezime"/></p>
                <p style="margin-left: 0.5em;">| <b>Ime</b></p>
                <p class="underline" style="width: 19%; margin-left:1em"><xsl:value-of select="lp:Licni_podaci/lp:Ime"/></p>
                <p style="margin-left: 0.5em;">| <b>Ime roditelja</b></p>
                <p class="underline" style="width: 19%; margin-left:1em;"><xsl:value-of select="b:Ime_roditelja"/></p>
            </div>
            <div class="row">
                <p class="info"><b>Pol</b></p>
                <xsl:if test="lp:Licni_podaci/lp:Pol = 'Muski'">
                    <input type="checkbox" disabled="disabled" name="male" checked="true" style="margin-top:1em;"/>
                </xsl:if>
                <xsl:if test="lp:Licni_podaci/lp:Pol != 'Muski'">
                    <input type="checkbox" name="male" disabled="disabled" style="margin-top:1em;"/>
                </xsl:if>
                <label for="male" style="margin-top:1em; margin-left: 0.3em;">M, </label>
                <xsl:if test="lp:Licni_podaci/lp:Pol = 'Zenski'">
                    <input type="checkbox" disabled="disabled" name="female" checked="true" style="margin-top:1em; margin-left: 0.3em;"/>
                </xsl:if>
                <xsl:if test="lp:Licni_podaci/lp:Pol != 'Zenski'">
                    <input type="checkbox" name="female" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;"/>
                </xsl:if>
                <label for="female" style="margin-top:1em; margin-left: 0.3em;">Ž</label>
                <p style="margin-left: 0.5em;">| <b>Datum rođenja</b></p>
                <p class="underline" style="width: 25%; margin-left:1em;"><xsl:value-of select="lp:Licni_podaci/lp:Datum_rodjenja"/></p>
                <p style="margin-left: 0.5em;">| <b>Mesto rođenja</b></p>
                <p class="underline" style="width: 25%; margin-left:1em;"><xsl:value-of select="b:Mesto_rodjenja"/></p>
            </div>
            <div class="row">
                <p class="info"><b>Adresa (ulica i broj)</b></p>
                <p class="underline" style="width: 33%"><xsl:value-of select="b:Adresa/b:Ulica"/> <xsl:value-of select="b:Adresa/b:Broj"/></p>
                <p style="margin-left: 0.5em;">| <b>Mesto/Naselje</b></p>
                <p class="underline" style="width: 22%; margin-left:1em;"><xsl:value-of select="b:Adresa/b:Mesto"/></p>
            </div>
            <div class="row">
                <p class="info"><b>Opština/Grad</b></p>
                <p class="underline" style="width: 36%"><xsl:value-of select="lp:Licni_podaci/lp:Opstina"/></p>
                <p style="margin-left: 0.5em;">| <b>Tel. fiksni</b></p>
                <p class="underline" style="width: 23%; margin-left:1em;"><xsl:value-of select="lp:Licni_podaci/lp:Kontakt/lp:Broj_fiksnog"/></p>
            </div>
            <div class="row">
                <p class="info"><b>Tel. mobilni</b></p>
                <p class="underline" style="width: 31.5%"><xsl:value-of select="lp:Licni_podaci/lp:Kontakt/lp:Broj_mobilnog"/></p>
                <p style="margin-left: 0.5em;">| <b>Imejl</b></p>
                <p class="underline" style="width: 31.5%; margin-left:1em;"><xsl:value-of select="lp:Licni_podaci/lp:Kontakt/lp:Email"/></p>
            </div>
            <div class="row">
                <p class="info"><b>Radni status:</b></p>
                <xsl:if test="b:Radni_status = 'zaposlen'">
                    <input type="checkbox" disabled="disabled" checked="true" style="margin-top:1em;"/>
                </xsl:if>
                <xsl:if test="b:Radni_status != 'zaposlen'">
                    <input type="checkbox" disabled="disabled" style="margin-top:1em;"/>
                </xsl:if>
                <label style="margin-top:1em; margin-left: 0.3em;">zaposlen, </label>

                <xsl:if test="b:Radni_status = 'nezaposlen'">
                    <input type="checkbox" disabled="disabled" checked="true" style="margin-top:1em; margin-left: 0.3em;"/>
                </xsl:if>
                <xsl:if test="b:Radni_status != 'nezaposlen'">
                    <input type="checkbox" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;"/>
                </xsl:if>
                <label style="margin-top:1em; margin-left: 0.3em;">nezaposlen, </label>

                <xsl:if test="b:Radni_status = 'penzioner'">
                    <input type="checkbox" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;" checked="true"/>
                </xsl:if>
                <xsl:if test="b:Radni_status != 'penzioner'">
                    <input type="checkbox" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;"/>
                </xsl:if>
                <label style="margin-top:1em; margin-left: 0.3em;">penzioner, </label>

                <xsl:if test="b:Radni_status = 'ucenik'">
                    <input type="checkbox" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;" checked="true"/>
                </xsl:if>
                <xsl:if test="b:Radni_status != 'ucenik'">
                    <input type="checkbox" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;"/>
                </xsl:if>
                <label style="margin-top:1em; margin-left: 0.3em;">učenik, </label>

                <xsl:if test="b:Radni_status = 'student'">
                    <input type="checkbox" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;" checked="true"/>
                </xsl:if>
                <xsl:if test="b:Radni_status != 'student'">
                    <input type="checkbox" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;"/>
                </xsl:if>
                <label style="margin-top:1em; margin-left: 0.3em;">student, </label>

                <xsl:if test="b:Radni_status = 'dete'">
                    <input type="checkbox" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;" checked="true"/>
                </xsl:if>
                <xsl:if test="b:Radni_status != 'dete'">
                    <input type="checkbox" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;"/>
                </xsl:if>
                <label style="margin-top:1em; margin-left: 0.3em;">dete </label>
            </div>

            <div class="row">
                <p class="info"><b>Zanimanje zaposlenog:</b></p>
                <xsl:if test="b:Zanimanje = 'zdra_zastita'">
                    <input type="checkbox" disabled="disabled" checked="true" style="margin-top:1em;"/>
                </xsl:if>
                <xsl:if test="b:Zanimanje != 'zdra_zastita'">
                    <input type="checkbox" disabled="disabled" style="margin-top:1em;"/>
                </xsl:if>
                <label style="margin-top:1em; margin-left: 0.3em;">zdravstvena zaštita, </label>

                <xsl:if test="b:Zanimanje = 'soc_zastita'">
                    <input type="checkbox" disabled="disabled" checked="true" style="margin-top:1em; margin-left: 0.3em;"/>
                </xsl:if>
                <xsl:if test="b:Zanimanje != 'soc_zastita'">
                    <input type="checkbox" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;"/>
                </xsl:if>
                <label style="margin-top:1em; margin-left: 0.3em;">socijalna zaštita, </label>

                <xsl:if test="b:Zanimanje = 'prosveta'">
                    <input type="checkbox" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;" checked="true"/>
                </xsl:if>
                <xsl:if test="b:Zanimanje != 'prosveta'">
                    <input type="checkbox" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;"/>
                </xsl:if>
                <label style="margin-top:1em; margin-left: 0.3em;">prosveta, </label>

                <xsl:if test="b:Zanimanje = 'mup'">
                    <input type="checkbox" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;" checked="true"/>
                </xsl:if>
                <xsl:if test="b:Zanimanje != 'mup'">
                    <input type="checkbox" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;"/>
                </xsl:if>
                <label style="margin-top:1em; margin-left: 0.3em;">MUP, </label>

                <xsl:if test="b:Zanimanje = 'vojska'">
                    <input type="checkbox" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;" checked="true"/>
                </xsl:if>
                <xsl:if test="b:Zanimanje != 'vojska'">
                    <input type="checkbox" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;"/>
                </xsl:if>
                <label style="margin-top:1em; margin-left: 0.3em;">Vojska RS, </label>

                <xsl:if test="b:Zanimanje = 'drugo'">
                    <input type="checkbox" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;" checked="true"/>
                </xsl:if>
                <xsl:if test="b:Zanimanje != 'drugo'">
                    <input type="checkbox" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;"/>
                </xsl:if>
                <label style="margin-top:1em; margin-left: 0.3em;">drugo </label>
            </div>
            <div class="row">
                <p class="info"><b>Korisnik ustanove soc. zašt.</b></p>
                <xsl:if test="b:Koristi_soc_zastitu/b:Vrednost = 'true'">
                    <input type="checkbox" name="yes" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;" checked="true"/>
                </xsl:if>
                <xsl:if test="b:Koristi_soc_zastitu/b:Vrednost != 'true'">
                    <input type="checkbox" name="yes" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;"/>
                </xsl:if>
                <label style="margin-top:1em; margin-left: 0.3em;">DA, </label>

                <xsl:if test="b:Koristi_soc_zastitu/b:Vrednost = 'false'">
                    <input type="checkbox" name="no" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;" checked="true"/>
                </xsl:if>
                <xsl:if test="b:Koristi_soc_zastitu/b:Vrednost != 'false'">
                    <input type="checkbox" name="no" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;"/>
                </xsl:if>
                <label style="margin-top:1em; margin-left: 0.3em;">NE </label>

                <p style="margin-left: 0.5em;">| <b>Naziv i opština sedišta</b></p>
				<xsl:if test="b:Koristi_soc_zastitu/b:Vrednost = 'true'">
					<p class="underline" style="width: 39%; margin-left:1em;"><xsl:value-of select="b:Koristi_soc_zastitu/b:Mesto"/> , opština <xsl:value-of select="b:Koristi_soc_zastitu/b:Opstina"/></p>
				</xsl:if>
            </div>

            <div class="row">
                <p class="info"><b>Izjavljujem da: </b></p>
                <xsl:if test="b:Izjava/b:Vrednost = 'true'">
                    <input type="checkbox" name="yes" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;" checked="true"/>
                </xsl:if>
                <xsl:if test="b:Izjava/b:Vrednost != 'true'">
                    <input type="checkbox" name="yes" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;"/>
                </xsl:if>
                <label style="margin-top:1em; margin-left: 0.3em;">SAGLASAN SAM, </label>

                <xsl:if test="b:Izjava/b:Vrednost = 'false'">
                    <input type="checkbox" name="yes" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;" checked="true"/>
                </xsl:if>
                <xsl:if test="b:Izjava/b:Vrednost != 'false'">
                    <input type="checkbox" name="yes" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;"/>
                </xsl:if>
                <label style="margin-top:1em; margin-left: 0.3em;">NISAM SAGLASAN </label>
                <p style="margin-left: 0.3em;">(označiti) sa sprovođenjem aktivne/pasivne imunizacije</p>
            </div>
            <div class="row">
				<p>(upisati naziv imunološkog leka):</p>
                <p class="underline" style="width: 52%; margin-left:1em;"><xsl:value-of select="b:Izjava/b:Lek"/></p>
            </div>
            <div class="row">
                <p class="info"><b>Lekar mi je objasnio prednosti i rizike od sprovođenja aktivne/pasivne imunizacije navedenim imunološkim
                    lekom.</b></p>
            </div>
            <div class="row">
                <p class="info"><b>Potpis pacijenta ili zakonskog zastupnika pacijenta</b></p>
                <p style="width: 40%; text-align: right;"><b>Datum: <xsl:value-of select="b:Datum"/></b></p>
            </div>
            <div class="row">
                <p class="info"><b>___________________________________________________</b></p>
                
            </div>
        </div>
    </xsl:template>

    <xsl:template match="b:Radnikov_deo">
        <div class="informations">
            <div class="row">
                <p class="info">''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''</p>
            </div>
            <div class="row">
                <h2 style="text-align: center; width: 75%">EVIDENCIJA O VAKCINACIJI PROTIV COVID-19</h2>
            </div>
            <div class="row">
                <h4 style="text-align: center; width: 75%; margin-top: 0em;">(popunjava zdravstveni radnik)</h4>
            </div>
            <div class="row">
                <p class="info">Zdravstvena ustanova</p>
                <p class="underline" style="width: 30%; margin-left:1em;"><xsl:value-of select="b:Zdravstvena_ustanova"/></p>
                <p class="info">Vakcinacijski punkt</p>
                <p class="underline" style="width: 20%; margin-left:1em;"><xsl:value-of select="b:Vakc_punkt"/></p>
            </div>
            <div class="row">
                <p class="info">Ime, prezime, faksimil i br. telefona lekara:</p>
                <p class="underline" style="width: 52%; margin-left:1em;"><xsl:value-of select="concat(b:Podaci_lekar/b:Ime, ' ', b:Podaci_lekar/b:Prezime)"/> , <xsl:value-of select="b:Podaci_lekar/b:Faksimil"/> , <xsl:value-of select="b:Podaci_lekar/b:Broj_telefona"/></p>
            </div>
            <div class="row">
                <p class="info" style="width: 74%;">Pre davanja vakcine pregledati osobu i upoznati je sa koristima i o mogućim neželjenim reakcijama posle
                    vakcinacije. Obavezno upisati svaku datu vakcinu i sve tražene podatke u ovaj obrazac i podatke uneti u lični
                    karton o izvršenim imunizacijama i zdravstveni karton. </p>
            </div>
            <div class="row">
                <table style="border-collapse: collapse; width: 74%;" class="info">
                    <tr>
                        <th>Naziv
                            vakcine</th>
                        <th>Datum davanja
                            vakcine
                            (V1 i V2)</th>
                        <th>Način
                            davanja
                            vakcine</th>
                        <th>Ekstremitet</th>
                        <th>Serija
                            vakcine
                            (lot)</th>
                        <th>Proizvođač</th>
                        <th>Neželjena reakcija</th>
                        <th>Potpis lekara</th>
                    </tr>
                    <xsl:for-each select="b:Vakcine/b:Vakcina">
                        <tr>
                            <td><xsl:value-of select="b:Naziv" /></td>
                            <td><xsl:value-of select="b:Datum_davanja" /></td>
                            <td><xsl:value-of select="b:Nacin_davanja" /></td>
                            <td>
                                <xsl:if test="b:Ekstremitet = 'DR'">
                                    <span style="border: 2px solid black; width: 1em; font-weight: bold;">1)</span>
                                </xsl:if>
                                <xsl:if test="b:Ekstremitet != 'DR'">
                                    <span>1)</span>
                                </xsl:if>
                                DR,
                                <xsl:if test="b:Ekstremitet = 'LR'">
                                    <span style="border: 1px solid black; width: 1em; font-weight: bold;">2)</span>
                                </xsl:if>
                                <xsl:if test="b:Ekstremitet != 'LR'">
                                    <span>2)</span>
                                </xsl:if>
                                LR</td>
                            <td><xsl:value-of select="b:Serija" /></td>
                            <td><xsl:value-of select="b:Proizvodjac" /></td>
                            <td><xsl:value-of select="b:Nuspojava" /></td>
                            <td />
                        </tr>
                    </xsl:for-each>
                    <tr>
                        <td colspan="8" style="text-align: left;">Privremene kontraindikacije
                            (datum utvrđivanja i dijagnoza): <xsl:value-of select="b:Privremene_nuspojave" /></td>
                    </tr>
                    <tr>
                        <td colspan="8" style="text-align: left;">Odluka komisije za trajne kontraindikacije (ako postoji, upisati Da)
							<xsl:if test="b:Odluka = 'true'">
                                    <span>DA</span>
                            </xsl:if>
						</td>
                    </tr>
                </table>
            </div>

            <div class="row">
                <p class="info"><b>Napomena:</b> Obrazac se čuva kao deo medicinske dokumentacije pacijenta.</p>
            </div>
        </div>
    </xsl:template>
</xsl:stylesheet>
