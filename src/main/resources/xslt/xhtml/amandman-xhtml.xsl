<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xs="http://www.w3.org/2001/XMLSchema"
                exclude-result-prefixes="xs"
                xmlns:am="http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman"
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
          .izmena{
          }
          .predmetizmene{
          font-size: 12pt;
          font-family: "Arial";
          }
          .resenje{
          font-size: 12pt;
          font-family: "Arial";
          padding-left: 1cm;
          font-style: italic;
          }
          .obrazlozenje{
          text-indent: 1cm;
          font-size: 11pt;
          font-family: "Arial";
          }
        </style>
      </head>
      <body>
        <xsl:apply-templates select="am:amandman"/>
      </body>
    </html>
  </xsl:template>

  <xsl:template match="am:amandman">
    <h1 id="{@meta:id}" class="amandman">
      <xsl:value-of select="translate(am:zaglavlje_amandman/meta:naziv/text(), $smallcase, $uppercase)"/>
    </h1>
    <div class="container">
      <xsl:apply-templates select="am:izmene"/>
      <xsl:apply-templates select="am:obrazlozenje"/>
    </div>
  </xsl:template>

  <xsl:template match="am:izmene">
    <div class="izmene">
      <xsl:apply-templates select="am:izmena"/>
    </div>
  </xsl:template>

  <xsl:template match="am:izmena">
    <div class="izmena">
      <h3>Član <xsl:number format="1." level="any" count="am:izmena"/></h3>
      <xsl:apply-templates select="am:predmet_izmene"/>
      <xsl:apply-templates select="am:resenja"/>
    </div>
    <br/>
  </xsl:template>

  <xsl:template match="am:predmet_izmene">
    <div class="predmetizmene">
      <p><xsl:value-of select="text()"/></p>
    </div>
  </xsl:template>

  <xsl:template match="am:resenja">
    <div class="resenja">
      <xsl:apply-templates select="am:resenje"/>
    </div>
  </xsl:template>

  <xsl:template match="am:resenje">
    <div class="resenje">
      "
      <xsl:apply-templates/>
      "
    </div>
  </xsl:template>

  <xsl:template match="am:obrazlozenje">
    <div>
      <h2>Obrazlozenje</h2>
      <h3>I Razlog podnosenja</h3>
      <xsl:apply-templates select="am:razlog_podnosenja"/>
      <h3>II Objasnjenje resenja</h3>
      <xsl:apply-templates select="am:objasnjenje_resenja"/>
      <h3>III Cilj</h3>
      <xsl:apply-templates select="am:cilj"/>
      <h3>IV Procena uticaja</h3>
      <xsl:apply-templates select="am:procena_uticaja"/>
    </div>
  </xsl:template>

  <xsl:template match="am:razlog_podnosenja">
    <div>
      <p class="obrazlozenje"><xsl:value-of select="text()"/></p>
    </div>
  </xsl:template>

  <xsl:template match="am:objasnjenje_resenja">
    <div>
      <p class="obrazlozenje"><xsl:value-of select="text()"/></p>
    </div>
  </xsl:template>

  <xsl:template match="am:cilj">
    <div>
      <p class="obrazlozenje"><xsl:value-of select="text()"/></p>
    </div>
  </xsl:template>

  <xsl:template match="am:procena_uticaja">
    <div>
      <p class="obrazlozenje"><xsl:value-of select="text()"/></p>
    </div>
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
