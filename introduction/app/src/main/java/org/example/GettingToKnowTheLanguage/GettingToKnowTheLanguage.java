package org.example.GettingToKnowTheLanguage;

import org.example.GettingToKnowTheLanguage.ClassesAndObjects.ClassesAndObjects;
import org.example.GettingToKnowTheLanguage.JavaLanguageBasics.JavaLanguageBasics;
import org.example.GettingToKnowTheLanguage.NumbersAndStrings.NumbersAndStrings;
import org.example.GettingToKnowTheLanguage.ObjectsClassesInterfacesPackagesAndInheritance.ObjectsClassesInterfacesPackagesAndInheritance;
import org.example.GettingToKnowTheLanguage.UsingRecordsToModelImmutableData.UsingRecordsToModelImmutableData;
import org.example.GettingToKnowTheLanguage.Inheritance.Inheritance;
import org.example.GettingToKnowTheLanguage.Interfaces.Interfaces;

import org.example.Utils.Module;

public class GettingToKnowTheLanguage implements Module {
  public void execute() {
    Module.display("Objects, Classes, Interfaces, Packages And Inheritance",
        new ObjectsClassesInterfacesPackagesAndInheritance());

    Module.display("Java Language Basics", new JavaLanguageBasics());

    Module.display("Classes and Objects", new ClassesAndObjects());

    Module.display("Using Records to Model Immutable Data", new UsingRecordsToModelImmutableData());

    Module.display("Numbers and Strings", new NumbersAndStrings());

    Module.display("Inheritance", new Inheritance());

    Module.display("Interfaces", new Interfaces());
  }
}
