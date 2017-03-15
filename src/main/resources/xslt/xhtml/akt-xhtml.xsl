<?xml version="1.0" encoding="UTF-8"?>   
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        xmlns:xs="http://www.w3.org/2001/XMLSchema"
        exclude-result-prefixes="xs"
        xmlns:akt="http://parlament.gov.rs/rs.ac.uns.ftn.model.akt"
        xmlns:meta="http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata"
        version="2.0">
        <xsl:variable name="smallcase" select="'abcdefghijklmnopqrstuvwxyzčćžđš'" />
        <xsl:variable name="uppercase" select="'ABCDEFGHIJKLMNOPQRSTUVWXYZČĆŽĐŠ'" />
        <xsl:template match="/">
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
                        
                        html {
                            font-family:"Arial";
                        }
                        .akt{
                            font-size: 18pt;
                            text-align: center;
                            font-weight: bold;
                        }
                        .deo{
                            font-size: 16pt;
                            text-align:center;
                            font-family:"Arial";
                            font-weight:bold;
                            
                        }
                        .glava{
                            font-size:16pt;
                            text-align: center;
                            font-family: "Arial";
                            font-weight: bold;
                        }
                        .clan{
                            font-size: 12pt;
                            text-align: center;
                            font-family: "Arial";
                            font-weight: bold;
                        }
                        .stav{
                            font-family: "Arial";
                            font-size: 11pt;
                            text-align: justify;
                            text-indent: 1cm;
                        }
                        .odeljak{
                            font-size: 12pt;
                            font-family: "Arial";
                            font-weight: bold;
                            padding-left: 1cm;
                        }
                        .pododeljak{
                            margin-top: 0.3cm;
                            font-family: "Arial";
                            font-size: 11pt;
                            text-align: justify;
                            padding-left: 1cm;
                        }
                        .tacka{
                            font-size: 11pt;
                            text-align: justify;
                            padding-left: 1cm;
                            margin-bottom: 0.3cm;
                        }
                        .podtacka{
                            font-size: 11pt;
                            text-align: justify;
                            padding-left: 1.5cm;
                        }
                        .alineja{
                            padding-left: 2cm;
                            font-size: 11pt;
                        }
                        
                    </style>
                </head>
                <body>
                   <xsl:apply-templates select="akt:akt"></xsl:apply-templates>
                </body>
            </html>
        </xsl:template> 
    
        <xsl:template match="akt:akt">
            <h1 id="{@meta:id}" class="akt">
                <xsl:value-of select="translate(akt:zaglavlje/meta:naziv/text(), $smallcase, $uppercase)"></xsl:value-of>
            </h1>
            <div class="container">
                <xsl:apply-templates></xsl:apply-templates>
            </div>
        </xsl:template>
        <xsl:template match="akt:deo">
            <h2 class="deo" id="{@meta:id}"> DEO <xsl:value-of select="translate(@redniBroj,$smallcase, $uppercase)"></xsl:value-of></h2>
            <h2 class="deo"> <xsl:value-of select="translate(@meta:naziv, $smallcase, $uppercase)"></xsl:value-of></h2>
            <xsl:apply-templates select="akt:glava"></xsl:apply-templates>
        </xsl:template>
    
    <xsl:template match="akt:zaglavlje">
        
    </xsl:template>
    
        <xsl:template match="akt:glava">
            <h2 class="glava" id="{@meta:id}">
                <xsl:choose>
                    <xsl:when test="@meta:naziv != ''">
                        <xsl:number format="I. " value="position()"></xsl:number>
                        <xsl:value-of select="translate(@meta:naziv,$smallcase, $uppercase)"></xsl:value-of>
                    </xsl:when>
                    <xsl:otherwise>
                        GLAVA <xsl:number format="I" value="position()"></xsl:number>
                    </xsl:otherwise>
                </xsl:choose>
            </h2>
            <xsl:apply-templates></xsl:apply-templates>
        </xsl:template>
        <xsl:template match="akt:odeljak">
            <div class="odeljak" id="{@meta:id}">
                <xsl:number format="1. " count="akt:odeljak"></xsl:number>
                <xsl:value-of select="@meta:naziv"></xsl:value-of>
            </div>
            <xsl:apply-templates></xsl:apply-templates>
        </xsl:template>
        <xsl:template match="akt:pododeljak">
            <div class="pododeljak" id="{@meta:id}">
                <xsl:number format="a) " count="akt:pododeljak"></xsl:number>
                <xsl:value-of select="@meta:naziv"></xsl:value-of>
            </div>
            <xsl:apply-templates></xsl:apply-templates>
        </xsl:template>
        <xsl:template match="akt:clan">
            <h2 class="clan" id="{@meta:id}"> <xsl:value-of select="@meta:naziv"></xsl:value-of></h2>
            <h2 class="clan">Član <xsl:number format="1." level="any" count="akt:clan"></xsl:number></h2>
            <xsl:apply-templates></xsl:apply-templates>
        </xsl:template>
        <xsl:template match="akt:referenca">
            <a href="#{@meta:idRef}"><xsl:value-of select="text()"></xsl:value-of></a>
        </xsl:template>
        <xsl:template match="akt:alineja">
            <p id="{@meta:id}" class="alineja">
                - <xsl:value-of select="current()"></xsl:value-of>
            </p>
        </xsl:template>
        <xsl:template match="akt:tacka">
            <div id="{@meta:id}" class="tacka"><xsl:number format="1) " count="akt:tacka"></xsl:number> <xsl:value-of select="@meta:naziv"/></div>
            <xsl:apply-templates></xsl:apply-templates>
        </xsl:template>
        <xsl:template match="akt:stav">
            <p id="{@meta:id}" class="stav">
                <xsl:apply-templates></xsl:apply-templates>
            </p>
        </xsl:template>
        <xsl:template match="akt:podtacka">
            <div id="{@meta:id}" class="podtacka"><xsl:number format="(1) " count="akt:podtacka"></xsl:number>
                <xsl:value-of select="@meta:naziv"/></div>
            <xsl:apply-templates></xsl:apply-templates>
        </xsl:template>
    </xsl:stylesheet>