/*
 * Copyright 2017-2019 the original author or authors.
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
package org.springframework.data.jdbc.core.convert;

import java.sql.ResultSet;

import org.springframework.data.relational.core.mapping.PersistentPropertyPathExtension;
import org.springframework.data.relational.core.mapping.RelationalPersistentEntity;
import org.springframework.data.relational.domain.Identifier;
import org.springframework.jdbc.core.RowMapper;

/**
 * Maps a {@link ResultSet} to an entity of type {@code T}, including entities referenced. This {@link RowMapper} might
 * trigger additional SQL statements in order to load other members of the same aggregate.
 *
 * @author Jens Schauder
 * @author Oliver Gierke
 * @author Mark Paluch
 * @author Maciej Walkowiak
 * @author Bastian Wilhelm
 * @since 1.1
 */
public class EntityRowMapper<T> implements RowMapper<T> {

	private final RelationalPersistentEntity<T> entity;
	private final PersistentPropertyPathExtension path;
	private final Identifier identifier;

	private final NewJdbcConverter newConverter;

	@SuppressWarnings("unchecked")
	public EntityRowMapper(PersistentPropertyPathExtension path, Identifier identifier, NewJdbcConverter newConverter) {

		this.entity = (RelationalPersistentEntity<T>) path.getLeafEntity();
		this.path = path;
		this.identifier = identifier;
		this.newConverter = newConverter;
	}

	public EntityRowMapper(RelationalPersistentEntity<T> entity, NewJdbcConverter newConverter) {

		this.entity = entity;
		this.path = null;
		this.identifier = null;
		this.newConverter = newConverter;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public T mapRow(ResultSet resultSet, int rowNumber) {

		return newConverter.read(entity.getType(), resultSet);

		// return path == null ? converter.mapRow(entity, resultSet, rowNumber)
		// : converter.mapRow(path, resultSet, identifier, rowNumber);
	}
}
