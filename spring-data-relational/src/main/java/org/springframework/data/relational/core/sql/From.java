/*
 * Copyright 2019-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.relational.core.sql;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * {@code FROM} clause.
 *
 * @author Mark Paluch
 * @since 1.1
 */
public class From extends AbstractSegment {

	private final List<Table> tables;

	From(Table... tables) {
		this(Arrays.asList(tables));
	}

	From(List<Table> tables) {

		super(tables.toArray(new Table[] {}));

		this.tables = Collections.unmodifiableList(tables);
	}

	public List<Table> getTables() {
		return this.tables;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FROM " + StringUtils.collectionToDelimitedString(tables, ", ");
	}
}
