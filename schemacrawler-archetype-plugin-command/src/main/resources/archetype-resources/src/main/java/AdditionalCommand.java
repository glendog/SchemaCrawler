#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};


import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import schemacrawler.schema.Column;
import schemacrawler.schema.Schema;
import schemacrawler.schema.Table;
import schemacrawler.tools.executable.BaseSchemaCrawlerCommand;
import sf.util.StringFormat;

/**
 * SchemaCrawler command plug-in.
 * 
 * @see <a href="https://www.schemacrawler.com">SchemaCrawler</a>
 * 
 * @author Automatically generated by SchemaCrawler 15.04.01
 */
public class AdditionalCommand
  extends BaseSchemaCrawlerCommand
{

  private static final Logger LOGGER = Logger
    .getLogger(AdditionalCommand.class.getName());

  static final String COMMAND = "additional";

  protected AdditionalCommand()
  {
    super(COMMAND);
  }
  
  @Override
  public void execute()
    throws Exception
  {
    // TODO: Possibly process command-line options, which are available
    // in additionalConfiguration

    try (final PrintWriter writer = new PrintWriter(outputOptions
      .openNewOutputWriter());)
    {
      for (final Schema schema: catalog.getSchemas())
      {
        // SchemaCrawler will control output of log messages if you use
        // JDK logging
        LOGGER.log(Level.INFO, new StringFormat("Processing <%s>", schema));
        for (final Table table: catalog.getTables(schema))
        {
          writer.println("o--> " + table);
          for (final Column column: table.getColumns())
          {
            writer.println("     o--> " + column);
          }
        }
      }
    }
  }

}
