## [최주호] 스프링 강의 - Spring Boot Repository (Hibernate JPQL)

### Hibernate

스프링부트에서 Hibernate는 ORM(Object-Relational Mapping) 기술을 사용하여 데이터베이스와의 상호 작용을 쉽게 할 수 있도록 지원한다.

Hibernate를 사용하면 자바 객체와 데이터베이스 테이블 간의 매핑을 쉽게 설정할 수 있으며, 객체를 데이터베이스에 저장, 검색, 수정 및 삭제할 수 있다.

Hibername는 이러한 기능을 제공하기 위해 JPA(Java Persistence API)를 구현하며, JPA는 Java에서 ORM을 구현하기 위한 API 표준이다.

Entity 클래스를 작성하여 데이터베이스와 매핑을 설정한다. Entity 클래스는 데이터베이스 테이블과 매핑된다.

Hibernate를 사용하면 데이터베이스 연결, 트랜잭션 관리 등의 기능을 쉽게 구현할 수 있다.

또한, Hibernate는 캐싱, 지연로딩 등의 기능을 제공하여 성능을 향상시킬 수 있다.

### ORM

OM은 객체지향 프로그래밍 언어에서 데이터베이스에 저장된 데이터를 객체로 변환하는 기술을 말한다. 이때, 객체와 데이터베이스 사이의 매핑을 수동으로 정의하고 구현한다.

ORM은 객체지향 프로그래밍 언어와 관계형 데이터베이스 사이의 데이터를 변환하는 기술로, ORM 프레임워크가 자동으로 객체와 테이블 간의 매핑을 수행한다.

ORM은 JPA(Java Persistence API)와 같은 표준 API를 사용하여 데이터베이스와 연동할 수 있다.

즉, OM은 ORM의 일종으로 볼 수 있다. OM은 ORM보다 덜 복잡하지만, 매핑 작업을 직접 수행해야 한다.

ORM은 매핑 작업을 자동화하여 생산성을 높이고, 객체지향적인 코드 작성을 촉진한다.

### Entity

- @Entity를 붙이면 Hibernate가 관리하는 객체가 된다.
- @Id는 해당 필드를 Primary Key로 사용하겠다고 Hibernate에게 알려주는 어노테이션이다.
- @GeneratedValue(strategy = GenerationType.IDENTITY)로 Auto_Increment로 PK 자동증가를 설정한다.
- 필드에 아무런 어노테이션이 없으면 String은 varchar(255)로 설정된다.
- @GeneratedValue(strategy = GenerationType.IDENTITY) 이외에도 Hibernate에서는 다음과 같은 자동 생성 전략을 제공한다.
    1. GenerationType.AUTO: 데이터베이스에 따라 자동으로 GenerationType.IDENTITY, GenerationType.SEQUENCE, GenerationType.TABLE 중 하나를
       선택한다.
    2. GenerationType>SEQUENCE: 데이터베이스 시퀀스를 이용하여 PK값을 생성한다. 이 전략을 사용할 때는 @SequenceGenerator 어노테이션을 이용하여 시퀀스를 생성해야 한다.
    3. GenerationType.TABLE: 데이터베이스에 별도의 테이블을 생성하여 PK값을 관리한다. 이 전략을 사용할 때는 @TableGenerator 어노테이션을 이용하여 테이블을 생성해야 한다.
    4. GenerationType.IDENTITY: 데이터베이스의 Identity 컬럼을 이용하여 PK값을 생성한다. 이 전략을 사용할 때는 데이터베이스가 Identity 컬럼을 지원하는지 확인해야 한다.

### CreateNativeQuery

### CreateQuery (JPQL)

### 영속화

Hibernate에서 영속화(Persistence)는 엔티티 객체를 데이터베이스와 연결하여 영구적으로 저장하는 과정을 의미한다. 영속화를 하기 위해서는 엔티티 객체를 영속성 컨텍스트(Persistence
Context)에 추가해야 한다. 영속성 컨텍스트는 엔티티 객체를 관리하는 Hibernate의 핵심 메커니즘 중 하나로, 영속화된 엔티티 객체를 캐싱하고, 변경된 내용을 추적하여 데이터베이스에 반영한다.

Hibernate에서는 다음과 같은 영속화 관련 메소드를 제공한다.

1. persist(entity): 영속성 컨텍스트에 엔티티 객체를 추가하여 영속화한다. 이 때, 엔티티 객체는 데이터 베이스에 저장되지 않은 상태이며, 영속성 컨텍스트 내에만 존재한다. persist() 메소드는
   엔티티 객체를 반환하지 않으며, void 타입을 반환한다.
2. merge(entity) 엔티티 객체를 영속성 컨텍스트에 추가하거나, 이미 존재하는 엔티티 객체를 가져와서 변경된 내용을 데이터베이스에 반여한다. merge() 메소드는 변경된 엔티티 객체를 반환하며, 이를
   통해 엔티티 객체를 계속 사용할 수 있다.
3. flush(): 영속성 컨텍스트에 있는 엔티티 객체의 변경 내용을 데이터베이스에 반영한다. 이 때, 데이터베이스에 저장되지 않은 엔티티 객체는 데이터베이스에 저장되지 않는다. flush() 메소드는 void
   타입을 반환한다.
4. clear(): 영속성 컨텍스트에 있는 모든 엔티티 객체를 제거한다. 이 때, 변경된 내용은 데이터베이스에 반영되지 않는다. clear() 메소드는 void 타입을 반환한다.

### Repository Test
