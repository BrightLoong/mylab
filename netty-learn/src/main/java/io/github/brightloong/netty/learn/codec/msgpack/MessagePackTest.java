package io.github.brightloong.netty.learn.codec.msgpack;

import org.msgpack.MessagePack;
import org.msgpack.template.Templates;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author BrightLoong
 * @date 2018/11/19 21:47
 * @description
 */
public class MessagePackTest {
    public static void main(String[] args) throws IOException {
        List<String> src = new ArrayList<>();

        src.add("msgpack");
        src.add("brightloong");
        src.add("fortest");

        MessagePack messagePack = new MessagePack();
        byte[] raw = messagePack.write(src);

        List<String> read = messagePack.read(raw, Templates.tList(Templates.TString));

        System.out.println(read.get(0));
        System.out.println(read.get(1));
        System.out.println(read.get(2));
    }
}
