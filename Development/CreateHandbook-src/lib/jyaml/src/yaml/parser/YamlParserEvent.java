package yaml.parser;
public class YamlParserEvent implements ParserEvent
{
        int level = 0;

        public void event(String a)
        {
        }

        public void error(Exception e, int line)
        {
                System.out.println("Error near line " + line + ": " + e);
        }

        public void event(int c)
        {
                switch (c)
                {
                        case YamlParser.MAP_CLOSE:
                        case YamlParser.LIST_CLOSE:
                        case YamlParser.MAP_NO_OPEN:
                        case YamlParser.LIST_NO_OPEN:

                                level--;
                                break;
                }

                System.out.println(sp() + (char) c);

                switch (c)
                {
                        case YamlParser.LIST_OPEN:
                        case YamlParser.MAP_OPEN:

                                level++;
                                break;
                }
        }

        public void content(String a, String b)
        {
                System.out.println(sp() + a + " : <" + b + ">");
        }

        public void property(String a, String b)
        {
                System.out.println(sp() + "( " + a + " : <" + b + "> )");
        }

        private String sp()
        {
            if (level<0) return "";
                char[] cs = new char[level*4];
                for (int i=0; i<cs.length; i++)
                    cs[i] = ' ';
                return new String(cs);
        }
}
