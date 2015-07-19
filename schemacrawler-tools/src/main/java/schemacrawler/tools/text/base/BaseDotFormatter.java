/*
 * SchemaCrawler
 * http://www.schemacrawler.com
 * Copyright (c) 2000-2015, Sualeh Fatehi.
 * This library is free software; you can redistribute it and/or modify it under
 * the terms
 * of the GNU Lesser General Public License as published by the Free Software
 * Foundation;
 * either version 2.1 of the License, or (at your option) any later version.
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License
 * along with this
 * library; if not, write to the Free Software Foundation, Inc., 59 Temple
 * Place, Suite 330,
 * Boston, MA 02111-1307, USA.
 */

package schemacrawler.tools.text.base;


import static sf.util.Utility.isBlank;
import static sf.util.Utility.readResourceFully;

import java.awt.Color;
import java.util.logging.Logger;

import schemacrawler.schema.CrawlHeaderInfo;
import schemacrawler.schema.DatabaseInfo;
import schemacrawler.schema.JdbcDriverInfo;
import schemacrawler.schema.SchemaCrawlerInfo;
import schemacrawler.schemacrawler.SchemaCrawlerException;
import schemacrawler.tools.options.OutputOptions;
import schemacrawler.tools.options.TextOutputFormat;
import schemacrawler.tools.text.utility.html.Alignment;
import schemacrawler.tools.text.utility.html.TableCell;
import schemacrawler.tools.text.utility.html.TableRow;

/**
 * Text formatting of schema.
 *
 * @author Sualeh Fatehi
 */
public abstract class BaseDotFormatter<O extends BaseTextOptions>
  extends BaseFormatter<O>
{

  protected static final Logger LOGGER = Logger
    .getLogger(BaseDotFormatter.class.getName());

  protected BaseDotFormatter(final O options,
                             final boolean printVerboseDatabaseInfo,
                             final OutputOptions outputOptions)
    throws SchemaCrawlerException
  {
    super(options, printVerboseDatabaseInfo, outputOptions);
  }

  @Override
  public void begin()
  {
    final String text = readResourceFully("/dot.header.txt");
    formattingHelper.append(text).println();
  }

  @Override
  public void end()
    throws SchemaCrawlerException
  {
    formattingHelper.append("}").println();

    super.end();
  }

  @Override
  public void handle(final CrawlHeaderInfo crawlHeaderInfo)
  {
    if (options.isNoInfo() || crawlHeaderInfo == null)
    {
      return;
    }

    TableRow row;

    final String title = crawlHeaderInfo.getTitle();
    if (!isBlank(title))
    {
      row = new TableRow(TextOutputFormat.html);
      row.add(newTableCell(title, Alignment.left, true, Color.white, 2));

      formattingHelper.append(row.toString()).println();
    }

    row = new TableRow(TextOutputFormat.html);
    row
      .add(newTableCell("generated by", Alignment.right, false, Color.white, 1));
    row.add(newTableCell(crawlHeaderInfo.getSchemaCrawlerInfo(),
                         Alignment.left,
                         false,
                         Color.white,
                         1));

    formattingHelper.append(row.toString()).println();

    row = new TableRow(TextOutputFormat.html);
    row
      .add(newTableCell("generated on", Alignment.right, false, Color.white, 1));
    row.add(newTableCell(formatTimestamp(crawlHeaderInfo.getCrawlTimestamp()),
                         Alignment.left,
                         false,
                         Color.white,
                         1));

    formattingHelper.append(row.toString()).println();

    row = new TableRow(TextOutputFormat.html);
    row.add(newTableCell("database version",
                         Alignment.right,
                         false,
                         Color.white,
                         1));
    row.add(newTableCell(crawlHeaderInfo.getDatabaseInfo(),
                         Alignment.left,
                         false,
                         Color.white,
                         1));

    formattingHelper.append(row.toString()).println();

    row = new TableRow(TextOutputFormat.html);
    row.add(newTableCell("driver version",
                         Alignment.right,
                         false,
                         Color.white,
                         1));
    row.add(newTableCell(crawlHeaderInfo.getJdbcDriverInfo(),
                         Alignment.left,
                         false,
                         Color.white,
                         1));

    formattingHelper.append(row.toString()).println();
  }

  @Override
  public void handle(final DatabaseInfo dbInfo)
  {
    // No-op
  }

  @Override
  public void handle(final JdbcDriverInfo driverInfo)
  {
    // No-op
  }

  @Override
  public void handle(final SchemaCrawlerInfo schemaCrawlerInfo)
  {
    // No-op
  }

  @Override
  public void handleHeaderEnd()
    throws SchemaCrawlerException
  {
    if (options.isNoInfo())
    {
      return;
    }

    formattingHelper.append("      </table>    >").println();
    formattingHelper.append("    labeljust=r").println();
    formattingHelper.append("    labelloc=b").println();
    formattingHelper.append("  ];").println();
    formattingHelper.println();
  }

  @Override
  public void handleHeaderStart()
    throws SchemaCrawlerException
  {
    if (options.isNoInfo())
    {
      return;
    }
    formattingHelper.append("  graph [fontcolor=\"#999999\", ").println();
    formattingHelper.append("    label=<").println();
    formattingHelper
      .append("<table color=\"#999999\" border=\"1\" cellborder=\"0\" cellspacing=\"0\">")
      .println();
  }

  @Override
  public void handleInfoEnd()
    throws SchemaCrawlerException
  {
    // No-op
  }

  @Override
  public void handleInfoStart()
    throws SchemaCrawlerException
  {
    // No-op
  }

  protected TableCell newTableCell(final String text,
                                   final Alignment align,
                                   final boolean emphasizeText,
                                   final Color bgColor,
                                   final int colspan)
  {
    return new TableCell(text,
                         true,
                         0,
                         align,
                         emphasizeText,
                         "",
                         bgColor,
                         colspan,
                         TextOutputFormat.html);
  }

}
