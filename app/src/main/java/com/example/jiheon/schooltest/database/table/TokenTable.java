package com.example.jiheon.schooltest.database.table;

import com.orm.dsl.Column;
import com.orm.dsl.Table;
import com.orm.dsl.Unique;

@Table(name="token_table")
public class TokenTable {
    @Column(name="token")
    private String token;

    public TokenTable() { }

    public TokenTable(String token) {
        this.token = token;
    }

    public String getToken() { return token; }
}
