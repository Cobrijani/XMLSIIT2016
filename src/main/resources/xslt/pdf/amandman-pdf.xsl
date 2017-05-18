<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xs="http://www.w3.org/2001/XMLSchema"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                xmlns:am="http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman"
                xmlns:akt="http://parlament.gov.rs/rs.ac.uns.ftn.model.akt"
                xmlns:meta="http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata"
                exclude-result-prefixes="xs"
                version="2.0">

  <xsl:variable name="smallcase" select="'abcdefghijklmnopqrstuvwxyzčćžđš'" />
  <xsl:variable name="uppercase" select="'ABCDEFGHIJKLMNOPQRSTUVWXYZČĆŽĐŠ'" />


  <xsl:template match="am:amandman">

    <fo:block id="{@meta:id}" font-size="18pt" text-align="center" font-family="Arial" font-weight="bold" margin-bottom="0.5cm">
      <xsl:value-of select="translate(am:zaglavlje_amandman/meta:naziv/text(), $smallcase, $uppercase)"/>
    </fo:block>
    <xsl:apply-templates select="am:izmene"/>
    <xsl:apply-templates select="am:obrazlozenje"/>
  </xsl:template>

  <xsl:template match="am:izmene">
      <xsl:apply-templates select="am:izmena"/>
  </xsl:template>

  <xsl:template match="am:izmena">
    <fo:block id="{@meta:id}" font-size="18pt" text-align="center" font-family="Arial" margin-bottom="1cm">
      <fo:block font-size="13pt" text-align="center" font-family="Arial" font-weight="bold" margin-bottom="0.3cm">
        Član <xsl:number format="1." level="any" count="am:izmena"/>
      </fo:block>
      <xsl:apply-templates select="am:predmet_izmene"/>
      <xsl:apply-templates select="am:resenja"/>
    </fo:block>
  </xsl:template>

  <xsl:template match="am:predmet_izmene">
    <fo:block id="{@meta:id}" font-family="Arial" font-size="11pt" text-align="justify" text-indent="1cm" margin-bottom="0.3cm">
      <xsl:value-of select="text()"/>
    </fo:block>
  </xsl:template>

  <xsl:template match="am:resenja">
      <xsl:apply-templates select="am:resenje"/>
  </xsl:template>

  <xsl:template match="am:resenje">
    <fo:block id="{@meta:id}" font-family="Arial" font-size="11pt" text-align="justify" margin-left = "0.5cm" margin-right="0.5cm" margin-bottom="0.3cm">
      "
      <xsl:apply-templates/>
      "
    </fo:block>
  </xsl:template>

  <xsl:template match="am:obrazlozenje">
      <fo:block font-size="14pt" text-align="center" font-family="Arial" font-weight="bold" margin-bottom="0.8cm">Obrazlozenje</fo:block>
      <fo:block font-size="12pt" text-align="center" font-family="Arial" font-weight="bold" margin-bottom="0.3cm">I Razlog podnosenja</fo:block>
      <xsl:apply-templates select="am:razlog_podnosenja"/>
      <fo:block font-size="12pt" text-align="center" font-family="Arial" font-weight="bold" margin-bottom="0.3cm">II Objasnjenje resenja</fo:block>
      <xsl:apply-templates select="am:objasnjenje_resenja"/>
      <fo:block font-size="12pt" text-align="center" font-family="Arial" font-weight="bold" margin-bottom="0.3cm">III Cilj</fo:block>
      <xsl:apply-templates select="am:cilj"/>
      <fo:block font-size="12pt" text-align="center" font-family="Arial" font-weight="bold" margin-bottom="0.3cm">IV Procena uticaja</fo:block>
      <xsl:apply-templates select="am:procena_uticaja"/>
  </xsl:template>

  <xsl:template match="am:razlog_podnosenja">
    <fo:block id="{@meta:id}" font-family="Arial" font-size="11pt" text-align="justify" text-indent="1cm" margin-bottom="0.5cm">
      <xsl:value-of select="text()"/>
    </fo:block>
  </xsl:template>

  <xsl:template match="am:objasnjenje_resenja">
    <fo:block id="{@meta:id}" font-family="Arial" font-size="11pt" text-align="justify" text-indent="1cm" margin-bottom="0.5cm">
      <xsl:value-of select="text()"/>
    </fo:block>
  </xsl:template>

  <xsl:template match="am:cilj">
    <fo:block id="{@meta:id}" font-family="Arial" font-size="11pt" text-align="justify" text-indent="1cm" margin-bottom="0.5cm">
      <xsl:value-of select="text()"/>
    </fo:block>
  </xsl:template>

  <xsl:template match="am:procena_uticaja">
    <fo:block id="{@meta:id}" font-family="Arial" font-size="11pt" text-align="justify" text-indent="1cm" margin-bottom="0.5cm">
      <xsl:value-of select="text()"/>
    </fo:block>
  </xsl:template>





  <!--
      Члан 24.
      Члан садржи једну или више норми које представљају једну
      логичку целину.
      Члан се означава речју „члан” са одговарајућим арапским
      бројем иза којег се ставља тачка, почевши од броја један, а затим по
      редоследу бројевима до последњег члана у пропису (нпр.: Члан 1.).
      Члан, по правилу, има назив који се уписује изнад бројчане
      ознаке члана.
      Назив члана треба да је кратак и да одговара његовој
      садржини.
      Изузетно од става 3. овог члана, ако шира класификациона
      јединица која има назив садржи само један члан, члан не мора имати назив.

      Унутрашња подела члана
      Члан 25.
      Члан се дели на ставове, ставови на тачке, тачке на подтачке,
      а подтачке на алинеје.
      Члан, став, тачка, подтачка и алинеја састоје се од једне
      реченице, а изузетно, ако је то потребно ради разумљивости, од две или
      више реченица.
      Одредбе члана и става увек се завршавају интерпункцијским
      знаком „тачка”, а одредбе тачке, подтачке и алинеје интерпункцијским
      знаком „тачка и запета” или „запета”, а на крају текста увек се ставља тачка.
  -->
  <xsl:template match="akt:clan">
    <fo:block keep-together.within-page="always">
      <fo:block id="{@meta:id}" font-size="12pt" text-align="center" font-family="Arial" margin-bottom="0.3cm">
        <xsl:value-of select="@meta:naziv"></xsl:value-of>
      </fo:block>
      <fo:block font-size="12pt" text-align="center" font-family="Arial">
        Član <xsl:number format="1." level="any" count="akt:clan"></xsl:number>
      </fo:block>
      <fo:block margin-bottom="0.5cm">
        <xsl:apply-templates></xsl:apply-templates>
      </fo:block>
    </fo:block>
  </xsl:template>
  <!--
      Став
      Члан 26.
      Члан се састоји од једног или више ставова.
      Сваки став почиње новим редом.
  -->
  <xsl:template match="akt:stav">
    <fo:block><xsl:value-of select="'&#x2028;'"/></fo:block>
    <fo:block id="{@meta:id}" font-family="Arial" font-size="11pt" text-align="justify" text-indent="1cm" margin-bottom="0.3cm">
      <xsl:apply-templates></xsl:apply-templates>
    </fo:block>
  </xsl:template>



  <xsl:template match="akt:referenca">
    <fo:basic-link internal-destination="{@meta:idRef}" color="blue" >
      <xsl:value-of select="text()"></xsl:value-of>
    </fo:basic-link>
  </xsl:template>
  <!-- Тачка
      Члан 27.
      Став се може састојати од две или више тачака.
      Тачка се означава арапским бројем са другим делом заграде,
      почевши од броја један, а затим по редоследу бројевима до последње тачке
      у ставу (нпр.: 1)).
      Свака тачка почиње новим редом.
  -->
  <xsl:template match="akt:tacka">
    <fo:block><xsl:value-of select="'&#x2028;'"/></fo:block>
    <fo:block id="{@meta:id}" font-family="Arial" font-size="11pt" text-align="justify" start-indent="1cm" margin-bottom="0.2cm" margin-top="0.2cm">
      <xsl:number format="1) " count="akt:tacka"></xsl:number> <xsl:value-of select="@meta:naziv"/>
    </fo:block>
    <fo:block>
      <xsl:apply-templates></xsl:apply-templates>
    </fo:block>
  </xsl:template>
  <!--
      Подтачка
      Члан 28.
      Тачка се може састојати од две или више подтачака.
      Подтачка се означава арапским бројем у загради (нпр.: (1)).
      Свака подтачка почиње новим редом.
  -->
  <xsl:template match="akt:podtacka">
    <fo:block><xsl:value-of select="'&#x2028;'"/></fo:block>
    <fo:block id="{@meta:id}" font-family="Arial" font-size="11pt" text-align="justify" start-indent="1.5cm" margin-bottom="0.2cm">
      <xsl:number format="(1) " count="akt:podtacka"></xsl:number> <xsl:value-of select="@meta:naziv"/>
    </fo:block>
    <fo:block>
      <xsl:apply-templates></xsl:apply-templates>
    </fo:block>
  </xsl:template>
  <!--
  Алинеја
  Члан 29.
  Подтачка се може састојати од две или више алинеја.
  Алинеја се означава водоравном цртом.
  Свака алинеја почиње новим редом.
  -->
  <xsl:template match="akt:alineja">
    <fo:block><xsl:value-of select="'&#x2028;'"/></fo:block>
    <fo:block id="{@meta:id}" start-indent="2cm" margin-bottom="0.2cm">
      - <xsl:value-of select="current()"></xsl:value-of>
    </fo:block>
  </xsl:template>

  <xsl:template match="/">
    <fo:root>
      <fo:layout-master-set>
        <fo:simple-page-master master-name="amandman-page"
                               page-height="29.7cm"
                               page-width="21cm"
                               margin-top="1cm"
                               margin-bottom="2cm"
                               margin-left="2.5cm"
                               margin-right="2.5cm">
          <fo:region-body
            region-name="amandman-body"
            margin-bottom="1.5cm"/>
          <fo:region-after extent="1cm"
                           region-name="amandman-footer"/>
        </fo:simple-page-master>
      </fo:layout-master-set>
      <fo:page-sequence master-reference="amandman-page"
                        master-name="amandman-pages"
                        initial-page-number="1">
        <fo:static-content flow-name="amandman-footer">
          <fo:block font-size="11pt" font-weight="normal" font-family="Arial" text-align="right">
            <fo:page-number/>
          </fo:block>
        </fo:static-content>

        <fo:flow flow-name="amandman-body">
          <fo:block>
            <xsl:apply-templates select="am:amandman"></xsl:apply-templates>
          </fo:block>
        </fo:flow>
      </fo:page-sequence>
    </fo:root>
  </xsl:template>
</xsl:stylesheet>
