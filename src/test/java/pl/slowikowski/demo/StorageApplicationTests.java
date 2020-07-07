package pl.slowikowski.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StorageApplicationTests {

    @Test
    void contextLoads() {
    }

//    @Test
//    public void testEmbeddedPg() throws Exception
//    {
//        try (EmbeddedPostgres pg = EmbeddedPostgres.start();
//             Connection c = pg.getPostgresDatabase().getConnection()) {
//            Statement s = c.createStatement();
//            ResultSet rs = s.executeQuery("SELECT 1");
//            assertTrue(rs.next());
//            assertEquals(1, rs.getInt(1));
//            assertFalse(rs.next());
//        } catch (Exception e) {
//            System.out.print(e);
//            fail();
//        }
//    }

}
