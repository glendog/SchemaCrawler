/*
 *
 * SchemaCrawler
 * http://www.schemacrawler.com
 * Copyright (c) 2000-2015, Sualeh Fatehi.
 *
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation;
 * either version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * library; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307, USA.
 *
 */

package schemacrawler.schema;


import java.util.List;
import java.util.Optional;

/**
 * Represents a database procedure.
 *
 * @author Sualeh Fatehi
 */
public interface Procedure
  extends Routine
{

  /**
   * Gets a column by unqualified name.
   *
   * @param name
   *        Unqualified name
   * @return Column, or null if not found.
   */
  @Deprecated
  @Override
  default ProcedureColumn getColumn(final String name)
  {
    return lookupColumn(name).orElse(null);
  }

  /**
   * Gets the list of columns in ordinal order.
   *
   * @return Columns of the procedure
   */
  @Override
  List<ProcedureColumn> getColumns();

  /**
   * Gets the procedure type.
   *
   * @return Procedure type
   */
  @Override
  ProcedureReturnType getReturnType();

  /**
   * Gets a column by unqualified name.
   *
   * @param name
   *        Name
   * @return Column of the procedure
   */
  @Override
  Optional<? extends ProcedureColumn> lookupColumn(String name);

}
