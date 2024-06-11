import com.kirilov.v9.DeprecatedClass;

module com.kirilov.java.playground.main {
    exports com.kirilov.v9 to com.kirilov.java.playground.test;
    provides DeprecatedClass with DeprecatedClass;

    exports com.kirilov.v15;
}