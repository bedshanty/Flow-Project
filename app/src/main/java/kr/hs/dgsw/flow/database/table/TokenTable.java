package kr.hs.dgsw.flow.database.table;

import com.orm.dsl.Column;
import com.orm.dsl.Table;

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
