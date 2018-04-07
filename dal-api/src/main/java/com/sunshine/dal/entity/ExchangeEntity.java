package com.sunshine.dal.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Index;

import javax.persistence.*;

@Entity
@Table(name = "tb_exchange")
@org.hibernate.annotations.Table(appliesTo = "tb_exchange", indexes = { @Index(columnNames = {"src_cur"}, name = "index_src_cur")
		, @Index(columnNames = {"dst_cur"}, name = "index_dst_cur") })
public class ExchangeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "src_cur", nullable = false)
	private String srcCurrency;

	@Column(name = "dst_cur", nullable = false)
	private String dstCurrency;

	@Column(name = "rate", nullable = false)
	private double rate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSrcCurrency() {
		return srcCurrency;
	}

	public void setSrcCurrency(String srcCurrency) {
		this.srcCurrency = srcCurrency;
	}

	public String getDstCurrency() {
		return dstCurrency;
	}

	public void setDstCurrency(String dstCurrency) {
		this.dstCurrency = dstCurrency;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
