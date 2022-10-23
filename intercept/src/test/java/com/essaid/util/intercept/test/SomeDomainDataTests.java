package com.essaid.util.intercept.test;

import com.essaid.util.intercept.SomeDomainData;
import com.essaid.util.intercept.data.BadDataMethod;
import com.essaid.util.intercept.data.DomainData;
import com.essaid.util.intercept.data.ExistingValueException;
import com.essaid.util.intercept.data.NonExistingValueException;
import com.essaid.util.intercept.data.NullValueException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SomeDomainDataTests {
  
  private SomeDomainData domainData;
  
  @Test
  @Order(1)
  void someDomainDataInstance() {
    this.domainData = new DomainData().as(SomeDomainData.class);
    Assertions.assertThat(domainData).isNotNull();
  }
  
  @Test
  @Order(2)
  void hasNameIsFalse() {
    Assertions.assertThat(domainData.hasName()).isFalse();
  }
  
  @Test
  @Order(2)
  void readNameIsNull() {
    Assertions.assertThat(domainData.readName()).isNull();
  }
  
  @Test
  @Order(3)
  void createName() {
    domainData.createName("Shahim");
  }
  
  @Test
  @Order(4)
  void hasNameIsTrue() {
    Assertions.assertThat(domainData.hasName()).isTrue();
  }
  
  @Test
  @Order(5)
  void nameEqualsShahim() {
    Assertions.assertThat(domainData.readName()).isEqualTo("Shahim");
  }
  
  @Test
  @Order(6)
  void createNameLaithThrowsException() {
    Assertions.assertThatExceptionOfType(ExistingValueException.class).isThrownBy(() -> domainData.createName("Laith"));
  }
  
  @Test
  @Order(7)
  void updateNameLaithReturnsShahim() {
    Assertions.assertThat(domainData.updateName("Laith")).isEqualTo("Shahim");
  }
  
  @Test
  @Order(8)
  void readNameIsLaith() {
    Assertions.assertThat(domainData.readName()).isEqualTo("Laith");
  }
  
  @Test
  @Order(9)
  void deletedNameIsNull() {
    Assertions.assertThat(domainData.deleteName()).isEqualTo("Laith");
    Assertions.assertThat(domainData.hasName()).isFalse();
  }
  
  @Test
  @Order(10)
  void clearedDataNoLongerHasName() {
    domainData.createName("Shahim");
    domainData.clearData();
    Assertions.assertThat(domainData.hasName()).isFalse();
    Assertions.assertThat(domainData.readName()).isNull();
  }
  
  @Test
  @Order(2)
  void badMethodNameThrowsException() {
    Assertions.assertThatExceptionOfType(BadDataMethod.class).isThrownBy(() -> domainData.badMethod());
  }
  
  @Test
  @Order(11)
  void getAndSetTests(){
    Assertions.assertThat(domainData.getName()).isNull();
    Assertions.assertThat(domainData.setName("Shahim")).isNull();
    Assertions.assertThat(domainData.setName("Adam")).isEqualTo("Shahim");
    Assertions.assertThat(domainData.getName()).isEqualTo("Adam");
    Assertions.assertThat(domainData.setName(null)).isEqualTo("Adam");
  }
  
  @Test
  @Order(12)
  void creatingWithNullThrowsNullValueException(){
    SomeDomainData someDomainData = getSomeDomainData();
    Assertions.assertThatThrownBy(() -> someDomainData.createName(null)).isInstanceOf(NullValueException.class);
    someDomainData.createName("Shahim");
    Assertions.assertThatThrownBy(() -> someDomainData.createName("Laith")).isInstanceOf(ExistingValueException.class);
    someDomainData.deleteName();
    Assertions.assertThatThrownBy(() -> someDomainData.updateName("Shahim")).isInstanceOf(NonExistingValueException.class);
  }
  
  SomeDomainData getSomeDomainData(){
    return new DomainData().as(SomeDomainData.class);
  }
  
}
