package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

public class V5__insert_default_productGroup extends BaseJavaMigration {
    public void migrate(Context context) {
        new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true)) //zapytanie SQL
                .execute("INSERT INTO public.product_groups (id, name, description) VALUES (1, 'SYSTEM', 'SYSTEM')");
    }
}
