using Rest.Models;

namespace Rest.Services
{
    public interface IVideoService
    {
        public Task<IEnumerable<Video>> GetAll();
        public Task<Video?> GetById(int id);
    }
}
