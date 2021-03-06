package com.craigburke.document.core

/**
 * Block element that holds text and images
 * @author Craig Burke
 */
class Paragraph extends BaseNode implements BlockNode {
	final static Margin DEFAULT_MARGIN = new Margin(top:12, bottom:12, left:0, right:0)

	Integer lineHeight
	BigDecimal lineHeightMultiplier = 1.1

	List children = []

	Integer getTextHeight() {
        BigDecimal result = lineHeight ?: lineHeightMultiplier * children.findAll {
            it.getClass() == Text }.inject(0f) { max, child -> Math.max(max, child.font.size as Float)
        }
        Math.ceil(result)
	}

    Integer getTextHeightOffset() {
        Math.ceil((textHeight - font.size) / 2)
    }

	String getText() {
		children.findAll { it.getClass() == Text }*.value.join('')
	}
}
