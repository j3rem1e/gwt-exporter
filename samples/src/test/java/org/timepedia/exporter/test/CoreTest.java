package org.timepedia.exporter.test;

import org.timepedia.exporter.client.Export;
import org.timepedia.exporter.client.ExportClosure;
import org.timepedia.exporter.client.ExportPackage;
import org.timepedia.exporter.client.Exportable;
import org.timepedia.exporter.client.ExporterUtil;
import org.timepedia.exporter.client.NoExport;

import simpledemo.client.SimpleDemo.MBase;
import simpledemo.client.SimpleDemo.MInterface;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.ui.Label;

public class CoreTest extends GWTTestCase{

  @Override
  public String getModuleName() {
    return "org.timepedia.exporter.Test";
  }
  
  public void test() {
    ExporterUtil.exportAll();
    runJsTests();
  }
  
  @ExportPackage("gwt")
  @Export
  public static class HelloAbstract implements Exportable {
    public String helloAbstract(){
      return this.getClass().getName();
    }
    
    @NoExport
    public String noHelloAbstract(){
      return this.getClass().getName();
    }
  }
  
  @ExportPackage("gwt")
  @Export
  public static class HelloClass extends HelloAbstract implements Exportable {
    public String hello(){
      return this.getClass().getName();
    }

    public static String[] test0(char c, byte b, int i, double d, float f, String s, Object o, Exportable e) {
      String[] ret = new String[8];
      ret[0] = "" + (int)c;
      ret[1] = "" + b;
      ret[2] = "" + i;
      ret[3] = "" + d;
      ret[4] = "" + f;
      ret[5] = "" + s;
      ret[6] = "" + o.getClass().getName();
      ret[7] = "" + e.getClass().getName();
      return ret;
    }

    public static int[] test1(char[]c, byte[] b, int[] i, double[]d, float[] f, long[] l, String[] s, Object[] o, Exportable[] e) {
      int[] ret = new int[9];
      ret[0] = c.length;
      ret[1] = b.length;
      ret[2] = i.length;
      ret[3] = d.length;
      ret[4] = f.length;
      ret[5] = l.length;
      ret[6] = s.length;
      ret[7] = o.length;
      ret[8] = e.length;
      return ret;
    }

    public static long[] test2() {
      return new long[]{1l,2l};
    }
    
    public static Exportable[] test3() {
      return new HelloClass[]{new HelloClass()};
    }
    
    public static char test4() {
      return 1;
    }
    
    public static byte test5() {
      return 2;
    }
    
    public static int test6() {
      return 3;
    }
    
    public static double test7() {
      return 4;
    }
    
    public static float test8() {
      return 5;
    }
    
    public static String test9() {
      return "A";
    }
    
    public static JavaScriptObject test10() {
      return new Label("").getElement();
    }
    
    public static long test11() {
      return 6;
    }
    
    public static String test12(long l){
      return "" + l;
    }
    
    public static long test13(long l, double d) {
      return l + (long)d;
    }
    
    public long test14(long l, double d, long[] a) {
      return l + (long)d + a[0];
    }
    
    public long[] test15(long[] a) {
      return a;
    }

    public static String test16(long l) {
      return "" + l;
    }

    public static long test16(long a, long b) {
      return (a + b);
    }

    public String test17(long l) {
      return "" + l;
    }

    public long test17(long a, long b) {
      return (a + b);
    }
  }
  
  @ExportPackage("gwt")
  @Export
  public static class Foo implements Exportable {
    
    String n = "foo";
    public Foo() {
    }
    public Foo(String id) {
      n= id;
    }
    public Foo(String id, String a) {
      n= id + a;
    }
    public String toString(){
      return n;
    }
    public String toString(String a){
      return n + ">" + a;
    }
    
    @ExportClosure
    public interface Closure extends Exportable {
      public String execute(String par1, String par2);
    }
    
    public String executeJsClosure(Closure closure){
       return closure.execute("Hello", "Friend");
    }
  }
  
  public static interface MInterface extends Exportable {
    @Export
    String m1();
    String m1(int a);
    @Export
    String m1(int a, int b);
  }
  
  public static abstract class MBase implements MInterface {
    @Export
    public String m0() {
      return "m0";
    }
    public String m1() {
      return "m1";
    }
    public String m1(int a) {
      return "m1-" + a;
    }
    public String m1(int a, int b) {
      return "m1-" + a + b;
    }
    @Export
    public String m2() {
      return "m2";
    }
  }
  
  @ExportPackage("gwt")
  public static class MClass extends MBase {
    @Export
    public String m0() {
      return "om0";
    }
    @Export
    public String m3() {
      return "m3";
    }
    public String m4() {
      return "m4";
    }
  }
  
  public static <T> void mAssertEqual(T a, T b) {
    assertEquals(a.toString(), b.toString());
//    if (a.toString().equals(b.toString())) {
//      System.out.println("OK -> " + b);
//    } else {
//      System.out.println("ERROR -> " + a.toString() + " <=> " + b.toString() + " ["
//          + a.getClass().getName() + ", " + b.getClass().getName() + "]");
//    }
  }
  
  public native JavaScriptObject runJsTests() /*-{
    assertEq = function(a, b) {@org.timepedia.exporter.test.CoreTest::mAssertEqual(Ljava/lang/Object;Ljava/lang/Object;)(a, b);}
    
    var v1 = new $wnd.gwt.CoreTest.Foo();
    assertEq("foo", v1);
    var v2 = new $wnd.gwt.CoreTest.Foo("foo2");
    assertEq("foo2", v2);
    var v3 = new $wnd.gwt.CoreTest.Foo("foo3", "bbb");
    assertEq("foo3bbb", v3);
    assertEq("foo3bbb>ccc", v3.toString("ccc"));
    assertEq("Hello,Friend", v3.executeJsClosure(function(arg1, arg2) {
        return arg1 + "," + arg2;
    }));
    
    var h = new $wnd.gwt.CoreTest.HelloClass();
    assertEq("1,2,3,4.0,5.0,S,com.google.gwt.core.client.JavaScriptObject$,org.timepedia.exporter.test.CoreTest$HelloClass", $wnd.gwt.CoreTest.HelloClass.test0(1, 2, 3, 4, 5, "S", window.document, h));
    assertEq("1,1,1,1,1,2,2,2,1", $wnd.gwt.CoreTest.HelloClass.test1([0], [0], [0], [0], [0], [1,2], ["a","b"], [window,document], [h]));
    assertEq("1,2", $wnd.gwt.CoreTest.HelloClass.test2());
    assertEq("org.timepedia.exporter.test.CoreTest$HelloClass", $wnd.gwt.CoreTest.HelloClass.test3()[0].hello());
    assertEq("org.timepedia.exporter.test.CoreTest$HelloClass", $wnd.gwt.CoreTest.HelloClass.test3()[0].helloAbstract());
    assertEq("undefined", "" + $wnd.gwt.CoreTest.HelloClass.test3()[0].noHelloAbstract);
    
    assertEq("1", "" + $wnd.gwt.CoreTest.HelloClass.test4(1, "A"));
    assertEq("2", "" + $wnd.gwt.CoreTest.HelloClass.test5());
    assertEq("3", "" + $wnd.gwt.CoreTest.HelloClass.test6());
    assertEq("4", "" + $wnd.gwt.CoreTest.HelloClass.test7());
    assertEq("5", "" + $wnd.gwt.CoreTest.HelloClass.test8());
    assertEq("A", "" + $wnd.gwt.CoreTest.HelloClass.test9());
    assertEq("div", "" + $wnd.gwt.CoreTest.HelloClass.test10().tagName.toLowerCase());
    assertEq("6", "" + $wnd.gwt.CoreTest.HelloClass.test11());
    assertEq("1", "" + $wnd.gwt.CoreTest.HelloClass.test12(1));
    assertEq("5", "" + $wnd.gwt.CoreTest.HelloClass.test13(2, 3));
    assertEq("4", "" + $wnd.gwt.CoreTest.HelloClass.test16(4));
    assertEq("14", "" + $wnd.gwt.CoreTest.HelloClass.test16(4, 10));
    var h = new $wnd.gwt.CoreTest.HelloClass();
    assertEq("102", "" + h.test14(1, 1, [100]));
    assertEq("100,200", "" + h.test15([100, 200]));
    assertEq("5", "" + h.test17(5));
    assertEq("15", "" + h.test17(5,10));
    
    var m = new $wnd.gwt.CoreTest.MClass();
    assertEq("om0", m.m0());
    assertEq("m1", m.m1());
    assertEq("m1-23", m.m1(2, 3));
    assertEq("m2", m.m2());
    assertEq("m2", m.m2());
    assertEq("m3", m.m3());
    
  }-*/;

}