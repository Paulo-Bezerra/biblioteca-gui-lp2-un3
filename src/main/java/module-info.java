module br.ufrn.imd.biblioteca {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens br.ufrn.imd.biblioteca to javafx.fxml;
    exports br.ufrn.imd.biblioteca;

    exports br.ufrn.imd.biblioteca.controller;
    opens br.ufrn.imd.biblioteca.controller to javafx.fxml;
}