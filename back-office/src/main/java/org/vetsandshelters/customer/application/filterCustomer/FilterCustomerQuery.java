package org.vetsandshelters.customer.application.filterCustomer;

public class FilterCustomerQuery {
    private Integer pageNumber;
    private Integer size;
    private String order;
    private String sort;
    private String name;
    private String surname;
    private String userAlias;
    private String docNumber;
    private String email;
    private String phone1;

    public FilterCustomerQuery(
            Integer offset,
            Integer size,
            String order,
            String sort,
            String name,
            String surname,
            String userAlias,
            String docNumber,
            String email,
            String phone1) {
        this.pageNumber = offset;
        this.size = size;
        this.order = order;
        this.sort = sort;
        this.name = name;
        this.surname = surname;
        this.userAlias = userAlias;
        this.docNumber = docNumber;
        this.email = email;
        this.phone1 = phone1;
    }

    public FilterCustomerQuery() {
        super();
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Integer getSize() {
        return size;
    }

    public String getOrder() {
        return order;
    }

    public String getSort() {
        return sort;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getUserAlias() {
        return userAlias;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone1() {
        return phone1;
    }

}
