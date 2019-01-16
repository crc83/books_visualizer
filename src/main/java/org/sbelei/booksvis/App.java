package org.sbelei.booksvis;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main(String[] args) {
        UdpipeFacade udpipe = new UdpipeFacade();

        String text = "У Сергійка було дев'ять яблук, а в Іринки 4.";
        String processed = udpipe.process(text);

        System.out.print(processed);
      }
}
