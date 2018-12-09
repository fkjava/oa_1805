package org.fkjava.disk.repository;

import org.fkjava.disk.domain.FileItem;
import org.fkjava.disk.domain.FileItem.ItemType;
import org.fkjava.identity.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FileItemRepository extends JpaRepository<FileItem, String>, JpaSpecificationExecutor<FileItem> {

	@Query("from FileItem item where item.user=:user and item.type=:type and item.fileInfo.name like :keyword")
	Page<FileItem> findByTypeAndNameLike(//
			@Param("user") User user, //
			@Param("type") ItemType file, //
			@Param("keyword") String keyword, //
			Pageable pageable);

	@Query("from FileItem item "//
			+ "where item.user = :user "//
			+ "and item.parent = :parent "//
			+ "and item.fileInfo.name = :name "//
			+ "and item.type = 'DIR'")
	FileItem findDirByParentAndName(@Param("user") User user, //
			@Param("parent") FileItem parent, //
			@Param("name") String name);

	@Query("from FileItem item "//
			+ "where item.user = :user "//
			+ "and item.parent is null "//
			+ "and item.fileInfo.name = :name "//
			+ "and item.type = 'DIR'")
	FileItem findDirByName(@Param("user") User user, //
			@Param("name") String name);

	Page<FileItem> findByUserAndType(User user, //
			ItemType file, //
			Pageable pageable);
}
