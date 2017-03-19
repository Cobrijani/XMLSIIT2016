<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:fo="http://www.w3.org/1999/XSL/Format"
    xmlns:akt="http://parlament.gov.rs/rs.ac.uns.ftn.model.akt"
    xmlns:meta="http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata"
    exclude-result-prefixes="xs"
    version="2.0">
    
    <xsl:variable name="smallcase" select="'abcdefghijklmnopqrstuvwxyzčćžđš'" />
    <xsl:variable name="uppercase" select="'ABCDEFGHIJKLMNOPQRSTUVWXYZČĆŽĐŠ'" />
    <xsl:template match="akt:preambula">
        <fo:block font-size="16pt" text-align="justify" font-family="Arial" margin-bottom="0.3cm" text-indent="1cm">
            <xsl:value-of select="text()"></xsl:value-of>
        </fo:block>
    </xsl:template>
    
    <xsl:template match="akt:zaglavlje">
        
    </xsl:template>
    
    <xsl:template match="akt:akt">
        <xsl:apply-templates select="akt:preambula"></xsl:apply-templates>
        <fo:block id="{@meta:id}" font-size="18pt" text-align="center" font-family="Arial" font-weight="bold" margin-bottom="0.5cm">
            <xsl:value-of select="translate(akt:zaglavlje/meta:naziv/text(), $smallcase, $uppercase)"></xsl:value-of>
        </fo:block>
        <xsl:apply-templates select="akt:deo"></xsl:apply-templates>
        <xsl:apply-templates select="akt:clan"></xsl:apply-templates>
        <xsl:apply-templates select="akt:glava"></xsl:apply-templates>
    </xsl:template>
    <!-- 
        Део
        Члан 20.
        Део обухвата тематску целину прописа и најшира је
        класификациона јединица прописа.
        Део се означава редним бројем, који се пише словима, према
        редоследу од броја један (нпр.: Део први) и може имати назив, који се,
        исписан великим словима, ставља испод ознаке.
        Ако се један део насловљава, назив морају имати сви делови
        прописа.
        Назив дела треба да је кратак и да одговара његовој садржини.
        Не треба користити делове као најшире класификационе
        јединице ако нема довољно материје да би се вршила даља подела на
        главе као непосредно уже класификационе јединице.
    -->
    <xsl:template match="akt:deo">
        <fo:block id="{@meta:id}" font-size="16pt" text-align="center" font-family="Arial" font-weight="bold" margin-bottom="0.5cm">
            DEO <xsl:value-of select="translate(@redniBroj, $smallcase, $uppercase)"></xsl:value-of>
        </fo:block>
        <fo:block font-size="16pt" text-align="center" font-family="Arial" font-weight="bold">
            <xsl:value-of select="translate(@meta:naziv, $smallcase, $uppercase)"></xsl:value-of>
        </fo:block>
        <fo:block>
            <xsl:apply-templates select="akt:glava"></xsl:apply-templates>
        </fo:block>
    </xsl:template>
    <!-- 
    Глава
    Члан 21.
    Део се дели најмање на две главе, којима се тематске целине
    деле на више функционалних или смисаоних целина.
    Глава се може означити на два начина:
    1) римским бројем без тачке (нпр.: Глава I), с тим да се испод
    ознаке напише назив главе великим словима;
    2) римским бројем са тачком, с тим да се у наставку у истом
    реду напише назив главе великим словима (нпр.: I. УВОДНЕ ОДРЕДБЕ).
    Глава се означава римским бројем почевши од броја један, а
    затим по редоследу бројевима до последње главе у пропису.
    Назив главе треба да је кратак и да одговара њеној садржини.
    -->
    <xsl:template match="akt:glava" >
                <fo:block id="{@meta:id}"  font-size="16pt" text-align="center" font-family="Arial" font-weight="bold" margin-bottom="0.5cm">
                    <xsl:choose>
                        <xsl:when test="@meta:naziv != ''">
                            <xsl:number format="I. " value="position()"></xsl:number>
                            <xsl:value-of select="translate(@meta:naziv,$smallcase, $uppercase)"></xsl:value-of>
                        </xsl:when>
                        <xsl:otherwise>
                            GLAVA <xsl:number format="I" value="position()"></xsl:number>
                        </xsl:otherwise>
                    </xsl:choose>
                </fo:block>
                <fo:block>
                    <xsl:apply-templates></xsl:apply-templates>
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
                <fo:block id="{@meta:id}" font-size="12pt" text-align="center" font-family="Arial" font-weight="bold" margin-bottom="0.3cm">
                    <xsl:value-of select="@meta:naziv"></xsl:value-of>
                </fo:block>
                <fo:block font-size="12pt" text-align="center" font-family="Arial" font-weight="bold">
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
 
    <!--
        Одељак
        Члан 22.
        Глава може бити подељена на одељке.
        Одељак се означава тако што се изнад текста одељка ставља
        назив, а испред назива одговарајући арапски број са тачком (нпр.: 1.
        Прелазне одредбе).
        Назив одељка треба да је кратак и да одговара његовој
        садржини.
      -->
    <xsl:template match="akt:odeljak">
        <fo:block id="{@meta:id}" font-size="12pt" font-family="Arial" font-weight="bold" start-indent="1cm" margin-bottom="0.5cm">
           <xsl:number format="1. " count="akt:odeljak"></xsl:number><xsl:value-of select="@meta:naziv"></xsl:value-of>
        </fo:block>
        <fo:block>
            <xsl:apply-templates></xsl:apply-templates>
        </fo:block>
    </xsl:template>
    <!-- 
        Пододељак
        Члан 23.
        Одељци се могу разврстати на пододељке, који се означавају
        тако што се изнад текста пододељка ставља назив, а испред назива
        одговарајуће мало слово азбуке са заградом (нпр.: а) Овлашћења
        надлежног органа).
        Назив пододељка треба да је кратак и да одговара његовој
        садржини.
    -->
    <xsl:template match="akt:pododeljak">
        <fo:block id="{@meta:id}" font-family="Arial" font-size="11pt" text-align="justify" start-indent="1cm" margin-bottom="0.5cm"> 
            <xsl:number format="a) " count="akt:pododeljak"></xsl:number> <xsl:value-of select="@meta:naziv"></xsl:value-of>
        </fo:block>
        <fo:block>
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
                <fo:simple-page-master master-name="akt-page"
                    page-height="29.7cm"
                    page-width="21cm"
                    margin-top="1cm"
                    margin-bottom="2cm"
                    margin-left="2.5cm"
                    margin-right="2.5cm">
                    <fo:region-body
                        region-name="akt-body"
                        margin-bottom="1.5cm"></fo:region-body>/>
                    <fo:region-after extent="1cm"
                         region-name="akt-footer"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="akt-page"
                master-name="akt-pages"
                initial-page-number="1">
                <fo:static-content flow-name="akt-footer">
                    <fo:block font-size="11pt" font-weight="normal" font-family="Arial" text-align="right">
                        <fo:page-number/>
                    </fo:block>
                </fo:static-content>
                
                <fo:flow flow-name="akt-body">
                    <fo:block>
                        <xsl:apply-templates select="akt:akt"></xsl:apply-templates>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>