<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" />
    <xsl:template match="/">
        <powerTools>
            <xsl:apply-templates />
        </powerTools>
    </xsl:template>
    <xsl:template match="tool">
        <tool>
            <xsl:for-each select="*[not(self::specifications)]">
                <xsl:attribute name="{name()}">
                    <xsl:value-of select="text()" />
                </xsl:attribute>
            </xsl:for-each>
            <xsl:copy-of select="specifications" />
        </tool>
    </xsl:template>
</xsl:stylesheet>