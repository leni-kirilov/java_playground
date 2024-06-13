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

    uses HttpClient; //from my requires, this specific Service/class I use

    // --------------------------- PROVIDING -----------------------

    //classes from this package are usable by ANY consumer
    exports v9;
    exports v10;
    exports v11;
    exports v12;
    exports v13;
    exports v14;
    exports v15;
    exports v16;
    exports v17;

    //classes from this package are usable only by a SPECIFIC customer
    exports v8 to org.junit.platform.commons;

//    //this specific interface by this particular implementation
//    provides WithDefaultMethod with OverwritingInterface;
}