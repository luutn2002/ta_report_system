# Entity examples

## Column

``` Java

  @Column(name = "name", nullable = false, unique = true)
  private String name;

```

## ManyToOne

``` Java

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Authority.class, optional = false)
  @JoinColumn(name = "authority_id", nullable = false)
  private Authority authority;

```

## OneToMany

``` Java

  @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY,
      targetEntity = Account.class, mappedBy = "authority")
  @Where(clause = "deleted = 'false'")
  @OrderBy("id asc")
  private List<Account> accounts;

```

## OneToOne

``` Java

  @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY,
      targetEntity = User.class, mappedBy = "account", optional = false)
  private User user;

```
