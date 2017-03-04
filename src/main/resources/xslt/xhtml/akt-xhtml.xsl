<?xml version="1.0" encoding="UTF-8"?>   
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        xmlns:xs="http://www.w3.org/2001/XMLSchema"
        exclude-result-prefixes="xs"
        xmlns:akt="http://parlament.gov.rs/rs.ac.uns.ftn.model.akt"
        xmlns:meta="http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata"
        version="2.0">
        <xsl:output method="html"></xsl:output>
        <xsl:variable name="smallcase" select="'abcdefghijklmnopqrstuvwxyzčćžđš'" />
        <xsl:variable name="uppercase" select="'ABCDEFGHIJKLMNOPQRSTUVWXYZČĆŽĐŠ'" />
        
        <xsl:template match="akt:akt">
            <html>
                <head>
                    <meta charset="utf-8"></meta>
                    <style type="text/css">
                        h1,h2,h3,h4,h5,h6 {
                            text-align: center;
                        }
                        p, div {
                            text-align: justify;
                        }
                        .container{
                            margin-left: 30%;
                            margin-right: 30%;
                        }w
                        p.stav{
                            text-indent: 5em;
                        }
                        
                    </style>
                    <title><xsl:value-of select="@meta:naziv"></xsl:value-of></title>
                </head>
                <body>
                    <h1 id="{@meta:id}">
                        
                          <xsl:value-of select="translate(@meta:naziv,$smallcase, $uppercase)"></xsl:value-of>
                    </h1>
                    <div class="container">
                        <xsl:apply-templates select="akt:deo"></xsl:apply-templates>
                    </div>
                </body>
            </html>
        </xsl:template> 
        <xsl:template match="akt:deo">
            <h2><xsl:value-of select="translate(@meta:naziv,$smallcase, $uppercase)"></xsl:value-of> DEO </h2>
            <xsl:apply-templates></xsl:apply-templates>
        </xsl:template>
        <xsl:template match="akt:glava">
            <h2>GLAVA <xsl:value-of select="translate(@redniBroj,$smallcase, $uppercase)"></xsl:value-of></h2>
            <h2><xsl:value-of select="translate(@meta:naziv,$smallcase, $uppercase)"></xsl:value-of></h2>
            <xsl:apply-templates></xsl:apply-templates>
        </xsl:template>
        <xsl:template match="akt:odeljak">
            <h2>Odeljak <xsl:value-of select="@redniBroj"></xsl:value-of></h2>
            <h2><xsl:value-of select="@meta:naziv"></xsl:value-of></h2>
            <xsl:apply-templates></xsl:apply-templates>
        </xsl:template>
        <xsl:template match="akt:pododeljak">
            <h2>Pododeljak <xsl:value-of select="@redniBroj"></xsl:value-of></h2>
            <h2><xsl:value-of select="@meta:naziv"></xsl:value-of></h2>
            <xsl:apply-templates></xsl:apply-templates>
        </xsl:template>
        <xsl:template match="akt:clan">
            <h2> <xsl:value-of select="@meta:naziv"></xsl:value-of></h2>
            <h2> <xsl:value-of select="akt:opis/text()"></xsl:value-of></h2>
            <h2>Član <xsl:value-of select="@redniBroj"></xsl:value-of></h2>
            <xsl:apply-templates></xsl:apply-templates>
        </xsl:template>
        <xsl:template match="akt:referenca">
            <a href="#{@meta:idRef}"><xsl:value-of select="text()"></xsl:value-of></a>
        </xsl:template>
        <xsl:template match="akt:alineja">
            <p id="{@meta:id}">
                <xsl:apply-templates />
            </p>
        </xsl:template>
        <xsl:template match="akt:opis">
            <p><xsl:value-of select="/text()"></xsl:value-of></p>
        </xsl:template>
        <xsl:template match="akt:tacka">
            <div id="{@meta:id}">Tacka <xsl:value-of select="@meta:naziv"></xsl:value-of></div>
            <xsl:apply-templates></xsl:apply-templates>
        </xsl:template>
        <xsl:template match="akt:stav">
            <p id="{@meta:id}" class="stav">
                <xsl:apply-templates></xsl:apply-templates>
            </p>
        </xsl:template>
        <xsl:template match="akt:potacka">
            <div id="{@meta:id}">Potacka<xsl:value-of select="@meta:naziv"></xsl:value-of></div>
            <xsl:apply-templates></xsl:apply-templates>
        </xsl:template>
    </xsl:stylesheet>