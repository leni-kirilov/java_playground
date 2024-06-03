import v8.model.OverwritingInterface;
import v8.model.WithDefaultMethod;

module com.kirilov.java.playground.test {

    // --------------------------- CONSUMING -----------------------
    //my module needs it and any consumer of my module must provide it
    requires org.junit.jupiter.api;

    //internally required. my users don't need to think about it
    requires static com.kirilov.java.playground.main;
    uses com.kirilov.v9.DeprecatedClass;


    //my consumers will automatically get access to these modules, too
    requires transitive java.desktop;
    uses javax.swing.JPanel; //from my requires, this specific Service/class I use

    // --------------------------- PROVIDING -----------------------

    //classes from this package are usable by ANY consumer
    exports v9;
    exports v10;

    //classes from this package are usable only by a SPECIFIC customer
    exports v8 to org.junit.platform.commons;

    //this specific interface by this particular implementation
    provides WithDefaultMethod with OverwritingInterface;
}