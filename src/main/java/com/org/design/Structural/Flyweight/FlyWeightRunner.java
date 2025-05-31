package com.org.design.Structural.Flyweight;

/**
 * <p>The Flyweight Pattern is used to minimize memory usage by sharing as much data as possible between
 * similar objects. It’s a structural design pattern that is especially helpful when dealing with a
 * large number of objects that share common data.</p>
 * <br>
 *
 * <p><b>Core Idea:</b> Separate intrinsic state (shared, unchanging data) from extrinsic state (unique, changing data), so you can reuse existing objects rather than create new ones.</p>
 * <h3>Real-World Use Cases of Flyweight Pattern</h3>
 *
 * <table border="1" cellpadding="4" cellspacing="0">
 *   <caption><b>Flyweight Pattern Examples</b></caption>
 *   <tr>
 *     <th>Use Case</th>
 *     <th>Explanation</th>
 *   </tr>
 *   <tr>
 *     <td>Text Editors</td>
 *     <td>Characters share font/style objects instead of creating new ones for each character.</td>
 *   </tr>
 *   <tr>
 *     <td>Game Development</td>
 *     <td>Tiles, enemies, or bullets reuse shared sprites or models with different positions.</td>
 *   </tr>
 *   <tr>
 *     <td>Document Rendering</td>
 *     <td>Elements like paragraphs or headers share formatting info (e.g., PDF, HTML engines).</td>
 *   </tr>
 *   <tr>
 *     <td>Web Browsers</td>
 *     <td>DOM elements with similar CSS styles share layout and rendering data.</td>
 *   </tr>
 *   <tr>
 *     <td>Object Pooling</td>
 *     <td>Heavy objects like database connections are reused instead of recreated.</td>
 *   </tr>
 *   <tr>
 *     <td>String Interning</td>
 *     <td>Java's <code>String.intern()</code> shares identical strings to save memory.</td>
 *   </tr>
 *   <tr>
 *     <td>Icon Libraries</td>
 *     <td>Icons are loaded once and reused across UI components (e.g., Material Icons).</td>
 *   </tr>
 * </table>
 *
 */


public class FlyWeightRunner {
    public static void main(String[] args) {
        client();
    }

    private static void client() {
        IRobot humanoidRobot1 = RoboticFactory.createRobot("HUMANOID");
        humanoidRobot1.display(1, 2);

        IRobot humanoidRobot2 = RoboticFactory.createRobot("HUMANOID");
        humanoidRobot2.display(10, 30);

        IRobot roboDog1 = RoboticFactory.createRobot("ROBOTICDOG");
        roboDog1.display(2, 9);

        IRobot roboDog2 = RoboticFactory.createRobot("ROBOTICDOG");
        roboDog2.display(11, 19);
    }
}
