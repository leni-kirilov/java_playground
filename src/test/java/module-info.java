import com.kirilov.v9.DeprecatedClass;

import java.net.http.HttpClient;

module com.kirilov.java.playground.test {

    // --------------------------- CONSUMING -----------------------
    //my module needs it and any consumer of my module must provide it
    requires org.junit.jupiter.api;

    //internally required. my users don't need to think about it
    requires static com.kirilov.java.playground.main;
    uses DeprecatedClass;

    //my consumers will automatically get access to these modules, too
    requires transitive java.desktop;
    requires java.net.http;
    requires jdk.incubator.vector;
    requires jdk.httpserver;

    uses HttpClient; //from my requires, this specific Service/class I use

    // --------------------------- PROVIDING -----------------------

    //classes from this package are usable by ANY consumer
    exports v8;

    //classes from this package are usable only by a SPECIFIC customer
    exports v9 to org.junit.platform.commons;
    exports v10 to org.junit.platform.commons;
    exports v11 to org.junit.platform.commons;
    exports v12 to org.junit.platform.commons;
    exports v13 to org.junit.platform.commons;
    exports v14 to org.junit.platform.commons;
    exports v15 to org.junit.platform.commons;
    exports v16 to org.junit.platform.commons;
    exports v17 to org.junit.platform.commons;
    exports v18 to org.junit.platform.commons;
    exports v19 to org.junit.platform.commons;
    exports v21 to org.junit.platform.commons;

    exports com.kirilov.interview.revolut.test to org.junit.platform.commons;

//    //this specific interface by this particular implementation
//    provides WithDefaultMethod with OverwritingInterface;
}