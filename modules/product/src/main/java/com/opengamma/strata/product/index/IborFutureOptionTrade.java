/**
 * Copyright (C) 2015 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.strata.product.index;

import java.io.Serializable;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.ImmutableDefaults;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.strata.basics.market.ReferenceData;
import com.opengamma.strata.collect.ArgChecker;
import com.opengamma.strata.product.ResolvableTrade;
import com.opengamma.strata.product.SecuritizedProductTrade;
import com.opengamma.strata.product.TradeInfo;

/**
 * A trade representing an option on a futures contract based on an Ibor index.
 * <p>
 * A trade in an underlying {@link IborFutureOption}.
 * The option is American, exercised at any point up to the exercise time.
 * Both daily margin and upfront premium styles are handled.
 * <p>
 * An Ibor future option is also known as a <i>STIR future option</i> (Short Term Interest Rate).
 */
@BeanDefinition(constructorScope = "package")
public final class IborFutureOptionTrade
    implements SecuritizedProductTrade, ResolvableTrade<ResolvedIborFutureOptionTrade>, ImmutableBean, Serializable {

  /**
   * The additional trade information, defaulted to an empty instance.
   * <p>
   * This allows additional information to be attached to the trade.
   * The trade date is required when calling {@link IborFutureOptionTrade#resolve(ReferenceData)}.
   */
  @PropertyDefinition(overrideGet = true)
  private final TradeInfo info;
  /**
   * The option that was traded.
   * <p>
   * The product captures the contracted financial details of the trade.
   */
  @PropertyDefinition(validate = "notNull", overrideGet = true)
  private final IborFutureOption product;
  /**
   * The quantity that was traded.
   * <p>
   * This is the number of contracts that were traded.
   * This will be positive if buying and negative if selling.
   */
  @PropertyDefinition(overrideGet = true)
  private final long quantity;
  /**
   * The price that was traded, in decimal form.
   * <p>
   * This is the price agreed when the trade occurred.
   */
  @PropertyDefinition(validate = "ArgChecker.notNegative", overrideGet = true)
  private final double price;

  //-------------------------------------------------------------------------
  @ImmutableDefaults
  private static void applyDefaults(Builder builder) {
    builder.info = TradeInfo.EMPTY;
  }

  //-------------------------------------------------------------------------
  @Override
  public ResolvedIborFutureOptionTrade resolve(ReferenceData refData) {
    ResolvedIborFutureOption resolved = getProduct().resolve(refData);
    return new ResolvedIborFutureOptionTrade(info, resolved, quantity, price);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code IborFutureOptionTrade}.
   * @return the meta-bean, not null
   */
  public static IborFutureOptionTrade.Meta meta() {
    return IborFutureOptionTrade.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(IborFutureOptionTrade.Meta.INSTANCE);
  }

  /**
   * The serialization version id.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static IborFutureOptionTrade.Builder builder() {
    return new IborFutureOptionTrade.Builder();
  }

  /**
   * Creates an instance.
   * @param info  the value of the property
   * @param product  the value of the property, not null
   * @param quantity  the value of the property
   * @param price  the value of the property
   */
  IborFutureOptionTrade(
      TradeInfo info,
      IborFutureOption product,
      long quantity,
      double price) {
    JodaBeanUtils.notNull(product, "product");
    ArgChecker.notNegative(price, "price");
    this.info = info;
    this.product = product;
    this.quantity = quantity;
    this.price = price;
  }

  @Override
  public IborFutureOptionTrade.Meta metaBean() {
    return IborFutureOptionTrade.Meta.INSTANCE;
  }

  @Override
  public <R> Property<R> property(String propertyName) {
    return metaBean().<R>metaProperty(propertyName).createProperty(this);
  }

  @Override
  public Set<String> propertyNames() {
    return metaBean().metaPropertyMap().keySet();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the additional trade information, defaulted to an empty instance.
   * <p>
   * This allows additional information to be attached to the trade.
   * The trade date is required when calling {@link IborFutureOptionTrade#resolve(ReferenceData)}.
   * @return the value of the property
   */
  @Override
  public TradeInfo getInfo() {
    return info;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the option that was traded.
   * <p>
   * The product captures the contracted financial details of the trade.
   * @return the value of the property, not null
   */
  @Override
  public IborFutureOption getProduct() {
    return product;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the quantity that was traded.
   * <p>
   * This is the number of contracts that were traded.
   * This will be positive if buying and negative if selling.
   * @return the value of the property
   */
  @Override
  public long getQuantity() {
    return quantity;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the price that was traded, in decimal form.
   * <p>
   * This is the price agreed when the trade occurred.
   * @return the value of the property
   */
  @Override
  public double getPrice() {
    return price;
  }

  //-----------------------------------------------------------------------
  /**
   * Returns a builder that allows this bean to be mutated.
   * @return the mutable builder, not null
   */
  public Builder toBuilder() {
    return new Builder(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      IborFutureOptionTrade other = (IborFutureOptionTrade) obj;
      return JodaBeanUtils.equal(info, other.info) &&
          JodaBeanUtils.equal(product, other.product) &&
          (quantity == other.quantity) &&
          JodaBeanUtils.equal(price, other.price);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(info);
    hash = hash * 31 + JodaBeanUtils.hashCode(product);
    hash = hash * 31 + JodaBeanUtils.hashCode(quantity);
    hash = hash * 31 + JodaBeanUtils.hashCode(price);
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(160);
    buf.append("IborFutureOptionTrade{");
    buf.append("info").append('=').append(info).append(',').append(' ');
    buf.append("product").append('=').append(product).append(',').append(' ');
    buf.append("quantity").append('=').append(quantity).append(',').append(' ');
    buf.append("price").append('=').append(JodaBeanUtils.toString(price));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code IborFutureOptionTrade}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code info} property.
     */
    private final MetaProperty<TradeInfo> info = DirectMetaProperty.ofImmutable(
        this, "info", IborFutureOptionTrade.class, TradeInfo.class);
    /**
     * The meta-property for the {@code product} property.
     */
    private final MetaProperty<IborFutureOption> product = DirectMetaProperty.ofImmutable(
        this, "product", IborFutureOptionTrade.class, IborFutureOption.class);
    /**
     * The meta-property for the {@code quantity} property.
     */
    private final MetaProperty<Long> quantity = DirectMetaProperty.ofImmutable(
        this, "quantity", IborFutureOptionTrade.class, Long.TYPE);
    /**
     * The meta-property for the {@code price} property.
     */
    private final MetaProperty<Double> price = DirectMetaProperty.ofImmutable(
        this, "price", IborFutureOptionTrade.class, Double.TYPE);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "info",
        "product",
        "quantity",
        "price");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 3237038:  // info
          return info;
        case -309474065:  // product
          return product;
        case -1285004149:  // quantity
          return quantity;
        case 106934601:  // price
          return price;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public IborFutureOptionTrade.Builder builder() {
      return new IborFutureOptionTrade.Builder();
    }

    @Override
    public Class<? extends IborFutureOptionTrade> beanType() {
      return IborFutureOptionTrade.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code info} property.
     * @return the meta-property, not null
     */
    public MetaProperty<TradeInfo> info() {
      return info;
    }

    /**
     * The meta-property for the {@code product} property.
     * @return the meta-property, not null
     */
    public MetaProperty<IborFutureOption> product() {
      return product;
    }

    /**
     * The meta-property for the {@code quantity} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Long> quantity() {
      return quantity;
    }

    /**
     * The meta-property for the {@code price} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Double> price() {
      return price;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 3237038:  // info
          return ((IborFutureOptionTrade) bean).getInfo();
        case -309474065:  // product
          return ((IborFutureOptionTrade) bean).getProduct();
        case -1285004149:  // quantity
          return ((IborFutureOptionTrade) bean).getQuantity();
        case 106934601:  // price
          return ((IborFutureOptionTrade) bean).getPrice();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      metaProperty(propertyName);
      if (quiet) {
        return;
      }
      throw new UnsupportedOperationException("Property cannot be written: " + propertyName);
    }

  }

  //-----------------------------------------------------------------------
  /**
   * The bean-builder for {@code IborFutureOptionTrade}.
   */
  public static final class Builder extends DirectFieldsBeanBuilder<IborFutureOptionTrade> {

    private TradeInfo info;
    private IborFutureOption product;
    private long quantity;
    private double price;

    /**
     * Restricted constructor.
     */
    private Builder() {
      applyDefaults(this);
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    private Builder(IborFutureOptionTrade beanToCopy) {
      this.info = beanToCopy.getInfo();
      this.product = beanToCopy.getProduct();
      this.quantity = beanToCopy.getQuantity();
      this.price = beanToCopy.getPrice();
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case 3237038:  // info
          return info;
        case -309474065:  // product
          return product;
        case -1285004149:  // quantity
          return quantity;
        case 106934601:  // price
          return price;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case 3237038:  // info
          this.info = (TradeInfo) newValue;
          break;
        case -309474065:  // product
          this.product = (IborFutureOption) newValue;
          break;
        case -1285004149:  // quantity
          this.quantity = (Long) newValue;
          break;
        case 106934601:  // price
          this.price = (Double) newValue;
          break;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
      return this;
    }

    @Override
    public Builder set(MetaProperty<?> property, Object value) {
      super.set(property, value);
      return this;
    }

    @Override
    public Builder setString(String propertyName, String value) {
      setString(meta().metaProperty(propertyName), value);
      return this;
    }

    @Override
    public Builder setString(MetaProperty<?> property, String value) {
      super.setString(property, value);
      return this;
    }

    @Override
    public Builder setAll(Map<String, ? extends Object> propertyValueMap) {
      super.setAll(propertyValueMap);
      return this;
    }

    @Override
    public IborFutureOptionTrade build() {
      return new IborFutureOptionTrade(
          info,
          product,
          quantity,
          price);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the additional trade information, defaulted to an empty instance.
     * <p>
     * This allows additional information to be attached to the trade.
     * The trade date is required when calling {@link IborFutureOptionTrade#resolve(ReferenceData)}.
     * @param info  the new value
     * @return this, for chaining, not null
     */
    public Builder info(TradeInfo info) {
      this.info = info;
      return this;
    }

    /**
     * Sets the option that was traded.
     * <p>
     * The product captures the contracted financial details of the trade.
     * @param product  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder product(IborFutureOption product) {
      JodaBeanUtils.notNull(product, "product");
      this.product = product;
      return this;
    }

    /**
     * Sets the quantity that was traded.
     * <p>
     * This is the number of contracts that were traded.
     * This will be positive if buying and negative if selling.
     * @param quantity  the new value
     * @return this, for chaining, not null
     */
    public Builder quantity(long quantity) {
      this.quantity = quantity;
      return this;
    }

    /**
     * Sets the price that was traded, in decimal form.
     * <p>
     * This is the price agreed when the trade occurred.
     * @param price  the new value
     * @return this, for chaining, not null
     */
    public Builder price(double price) {
      ArgChecker.notNegative(price, "price");
      this.price = price;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(160);
      buf.append("IborFutureOptionTrade.Builder{");
      buf.append("info").append('=').append(JodaBeanUtils.toString(info)).append(',').append(' ');
      buf.append("product").append('=').append(JodaBeanUtils.toString(product)).append(',').append(' ');
      buf.append("quantity").append('=').append(JodaBeanUtils.toString(quantity)).append(',').append(' ');
      buf.append("price").append('=').append(JodaBeanUtils.toString(price));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
