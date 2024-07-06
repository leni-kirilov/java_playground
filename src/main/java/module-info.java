import com.kirilov.v9.DeprecatedClass;

module com.kirilov.java.playground.main {
    exports com.kirilov.v9 to com.kirilov.java.playground.test;
    provides DeprecatedClass with DeprecatedClass;

    exports com.kirilov.v15;
    exports com.kirilov.v19 to com.kirilov.java.playground.test;
    exports com.kirilov.interview.revolut;
    exports com.kirilov.interview.revolut.loadbalancer;
    exports com.kirilov.interview;
}