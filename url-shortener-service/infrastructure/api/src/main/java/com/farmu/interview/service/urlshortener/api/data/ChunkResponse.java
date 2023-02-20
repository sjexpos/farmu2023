package com.farmu.interview.service.urlshortener.api.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A chunk of data restricted by the configured {@link PageableRequest}.
 *
 * @param <T>
 * @author Sergio Exposito
 */
abstract public class ChunkResponse<T> implements SliceResponse<T> {
	private static final long serialVersionUID = 867755909294344406L;

	private final List<T> content = new ArrayList<>();
	private final PageableRequest pageable;

	/**
	 * Creates a new {@link ChunkResponse} with the given content and the given governing {@link PageableRequest}.
	 *
	 * @param content must not be {@literal null}.
	 * @param pageable must not be {@literal null}.
	 */
	public ChunkResponse(List<T> content, PageableRequest pageable) {
		this.content.addAll(content);
		this.pageable = pageable;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.Slice#getNumber()
	 */
	public int getNumber() {
		return pageable.isPaged() ? pageable.getPageNumber() : 0;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.Slice#getSize()
	 */
	public int getSize() {
		return pageable.isPaged() ? pageable.getPageSize() : content.size();
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.Slice#getNumberOfElements()
	 */
	public int getNumberOfElements() {
		return content.size();
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.Slice#hasPrevious()
	 */
	public boolean hasPrevious() {
		return getNumber() > 0;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.Slice#isFirst()
	 */
	public boolean isFirst() {
		return !hasPrevious();
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.Slice#isLast()
	 */
	public boolean isLast() {
		return !hasNext();
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.Slice#nextPageable()
	 */
	public PageableRequest nextPageable() {
		return hasNext() ? pageable.next() : PageableRequest.unpaged();
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.Slice#previousPageable()
	 */
	public PageableRequest previousPageable() {
		return hasPrevious() ? pageable.previousOrFirst() : PageableRequest.unpaged();
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.Slice#hasContent()
	 */
	public boolean hasContent() {
		return !content.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.Slice#getContent()
	 */
	public List<T> getContent() {
		return Collections.unmodifiableList(content);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.Slice#getPageable()
	 */
	@Override
	public PageableRequest getPageable() {
		return pageable;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.Slice#getSort()
	 */
	@Override
	public SortRequest getSort() {
		return pageable.getSort();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ChunkResponse<?>)) {
			return false;
		}

		ChunkResponse<?> that = (ChunkResponse<?>) obj;

		boolean contentEqual = this.content.equals(that.content);
		boolean pageableEqual = this.pageable.equals(that.pageable);

		return contentEqual && pageableEqual;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		int result = 17;

		result += 31 * pageable.hashCode();
		result += 31 * content.hashCode();

		return result;
	}

}
